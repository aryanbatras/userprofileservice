package com.sashel.user_profile_service.seller.controller;

import com.sashel.user_profile_service.seller.dto.request.SellerRequestDto;
import com.sashel.user_profile_service.seller.dto.request.SellerRequestDtoStatus;
import com.sashel.user_profile_service.seller.dto.response.SellerResponseDto;
import com.sashel.user_profile_service.seller.entity.SellerEntity;
import com.sashel.user_profile_service.seller.service.SellerService;
import com.sashel.user_profile_service.utility.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/api/userprofile/seller")
public class SellerController {

    @Autowired
    SellerService sellerService;

    @Autowired
    S3Service s3Service;

    @GetMapping("/sellers")
    // http://sashel.in/admin/userprofile/seller/sellers?page=0&size=5&gender=female&isActive=true&minAge=18&maxAge=30
    // TODO: Return paginated list of SellerEntity objects for admin
    public ResponseEntity<Page<SellerEntity>> getSellers(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size,
            @RequestParam(required = false) String gender,
            @RequestParam(required = false) Boolean isActive,
            @RequestParam(required = false) Integer minAge,
            @RequestParam(required = false) Integer maxAge
    ) {
        return ResponseEntity.ok(
                sellerService.getAllSellersWithFilters(
                        gender, isActive, minAge, maxAge,
                        PageRequest.of(page, size)
                )
        );
    }

    @GetMapping("/{sellerId}")
    // TODO: Return SellerEntity object by Id
    public ResponseEntity<SellerResponseDto> getSeller(
            @PathVariable String sellerId
    ) {
        return ResponseEntity.ok().body(
                sellerService.getSeller(sellerId)
        );
    }

    @PutMapping("/{sellerId}")
    // TODO: Update SellerEntity object by Id
    public ResponseEntity<SellerResponseDto> updateSeller(
            @PathVariable String sellerId,
            @RequestBody SellerRequestDto sellerRequestDto
    ) {
        return ResponseEntity.ok().body(
                sellerService.updateSeller(sellerId, sellerRequestDto)
        );
    }

    @PutMapping("/{sellerId}/status")
    // TODO: Update SellerEntity object by Id
    public ResponseEntity<SellerResponseDto> updateSellerStatus(
            @PathVariable String sellerId,
            @RequestBody SellerRequestDtoStatus sellerRequestDtoStatus
            ) {
        return ResponseEntity.ok().body(
                sellerService.updateSellerStatus(sellerId, sellerRequestDtoStatus)
        );
    }

    @PostMapping("{sellerId}/profile-image")
    public ResponseEntity<?> uploadProfileImage(
            @RequestPart MultipartFile file,
            @RequestParam(required = false) String oldImageUrl,
            @PathVariable String userId
    ) throws IOException {
        String key = "profile_images/" + userId;
        String newImageUrl = s3Service.uploadFile(key, file, oldImageUrl);

        return ResponseEntity.ok(
                sellerService.updateProfileImage(userId, newImageUrl)
        );
    }


}
