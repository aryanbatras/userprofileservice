package com.sashel.user_profile_service.seller.controller;

import com.sashel.user_profile_service.seller.dto.request.SellerRequestDtoBrand;
import com.sashel.user_profile_service.seller.dto.response.SellerResponseDtoBrand;
import com.sashel.user_profile_service.seller.service.SellerServiceBrand;
import com.sashel.user_profile_service.utility.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/userprofile/seller")
public class SellerControllerBrand {

    @Autowired
    SellerServiceBrand sellerServiceBrand;

    @Autowired
    S3Service s3Service;

    @GetMapping("/{sellerId}/brand-info")
    // TODO: Return seller's brand info
    public ResponseEntity<SellerResponseDtoBrand> getBrandInfo(
            @PathVariable String sellerId
    ) {
        return ResponseEntity.ok().body(
                sellerServiceBrand.getBrandInfo(sellerId)
        );
    }

    @PostMapping("/{sellerId}/brand-info")
    // TODO: Create seller's brand info
    public ResponseEntity<SellerResponseDtoBrand> createBrandInfo(
            @PathVariable String sellerId,
            @RequestBody SellerRequestDtoBrand sellerRequestDtoBrand
    ) {
        return ResponseEntity.ok().body(
                sellerServiceBrand.createBrandInfo(sellerId, sellerRequestDtoBrand)
        );
    }

    @PutMapping("/{sellerId}/brand-info")
    // TODO: Update seller's brand info
    public ResponseEntity<SellerResponseDtoBrand> updateBrandInfo(
            @PathVariable String sellerId,
            @RequestBody SellerRequestDtoBrand sellerRequestDtoBrand
    ) {
        return ResponseEntity.ok().body(
                sellerServiceBrand.updateBrandInfo(sellerId, sellerRequestDtoBrand)
        );
    }

    @PostMapping("/{sellerId}/brand-logo")
    public ResponseEntity<String> uploadProfileImage(
            @PathVariable String sellerId,
            @RequestParam(required = false) String oldImageUrl,
            @RequestPart("image") MultipartFile image) throws IOException {

        String key = "brand_logo/" + sellerId;
        String newImageUrl = s3Service.uploadFile(key, image, oldImageUrl);

        return ResponseEntity.ok().body(
                sellerServiceBrand.updateBrandLogo(sellerId, newImageUrl)
        );

    }

}


