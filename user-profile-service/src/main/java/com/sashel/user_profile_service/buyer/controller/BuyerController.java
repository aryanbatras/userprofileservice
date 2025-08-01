package com.sashel.user_profile_service.buyer.controller;

import com.sashel.user_profile_service.buyer.dto.request.BuyerRequestDto;
import com.sashel.user_profile_service.buyer.dto.request.BuyerRequestDtoStatus;
import com.sashel.user_profile_service.buyer.dto.response.BuyerResponseDto;
import com.sashel.user_profile_service.buyer.entity.BuyerEntity;
import com.sashel.user_profile_service.buyer.service.BuyerService;
import com.sashel.user_profile_service.seller.dto.request.SellerRequestDtoStatus;
import com.sashel.user_profile_service.seller.dto.response.SellerResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/userprofile/buyer")
public class BuyerController {

    @Autowired
    BuyerService buyerService;

    // http://sashel.in/admin/userprofile/buyer/buyers?page=0&size=5&gender=female&isActive=true&minAge=18&maxAge=30
    // TODO: Return paginated list of BuyerEntity objects for admin
    @GetMapping("/buyers")
    public ResponseEntity<Page<BuyerEntity>> getBuyers(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size,
            @RequestParam(required = false) String gender,
            @RequestParam(required = false) Boolean isActive,
            @RequestParam(required = false) Integer minAge,
            @RequestParam(required = false) Integer maxAge
    ) {
        return ResponseEntity.ok(
                buyerService.getAllBuyersWithFilters(
                        gender, isActive, minAge, maxAge,
                        PageRequest.of(page, size)
                )
        );
    }

    // TODO: Return BuyerEntity object by Id
    @GetMapping("/buyer/{buyerId}")
    public ResponseEntity<BuyerResponseDto> getBuyer(
            @PathVariable String buyerId
    ) {
        return ResponseEntity.ok(
                buyerService.getBuyer(buyerId)
        );
    }

    // TODO: to update buyer's name, dob, gender
    @PutMapping("/buyer/{buyerId}")
    public ResponseEntity<BuyerResponseDto> updateBuyer(
            @PathVariable String buyerId,
            @RequestBody BuyerRequestDto buyerRequestDto
    ) {
        return ResponseEntity.ok(
                buyerService.updateBuyer(buyerId, buyerRequestDto)
        );
    }

    @PutMapping("/{buyerId}/status")
    // TODO: Update SellerEntity object by Id
    public ResponseEntity<BuyerResponseDto> updateSellerStatus(
            @PathVariable String sellerId,
            @RequestBody BuyerRequestDtoStatus buyerRequestDtoStatus
    ) {
        return ResponseEntity.ok().body(
                buyerService.updateBuyerStatus(sellerId, buyerRequestDtoStatus)
        );
    }

}
