package com.sashel.user_profile_service.seller.controller;

import com.sashel.user_profile_service.seller.dto.response.SellerResponseDtoKycVerify;
import com.sashel.user_profile_service.seller.service.SellerServiceKycVerification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/userprofile/seller")
public class SellerControllerKycVerification {

    @Autowired
    SellerServiceKycVerification sellerServiceKycVerification;

    @GetMapping("/{sellerId}/brand-kyc")
    // TODO: Return seller's full brand details for verification checking
    public ResponseEntity<SellerResponseDtoKycVerify> getBrandKycDetails(
            @PathVariable String sellerId
    ) {
        return ResponseEntity.ok().body(
                sellerServiceKycVerification.getBrandKycDetails(sellerId)
        );
    }

    // TODO: to verify pan
    @PostMapping("/{sellerId}/kyc-verify/pan")
    public ResponseEntity<Boolean> verifyPan(
            @PathVariable String sellerId,
            @RequestParam String value
    ) throws IOException, InterruptedException {
        return ResponseEntity.ok(
                sellerServiceKycVerification.verifyPan(sellerId, value)
        );
    }

    // TODO: to verify aadhar card
    @PostMapping("/{sellerId}/kyc-verify/aadhar")
    public ResponseEntity<Boolean> verifyAadhar(
            @PathVariable String sellerId,
            @RequestParam String value
    ) throws IOException, InterruptedException {
        return ResponseEntity.ok(
                sellerServiceKycVerification.verifyAadhar(sellerId, value)
        );
    }

    // TODO: to verify gst number
    @PostMapping("/{sellerId}/kyc-verify/gst")
    public ResponseEntity<Boolean> verifyGst(
            @PathVariable String sellerId,
            @RequestParam String value
    ) throws IOException, InterruptedException {
        return ResponseEntity.ok(
                sellerServiceKycVerification.verifyGst(sellerId, value)
        );
    }

    // TODO: to verify bank
    @PostMapping("/{sellerId}/kyc-verify/bank")
    public ResponseEntity<Boolean> verifyBank(
            @PathVariable String sellerId,
            @RequestParam String accountNumber,
            @RequestParam String ifscCode
    ) throws IOException, InterruptedException {
        return ResponseEntity.ok(
                sellerServiceKycVerification.verifyBank(sellerId, accountNumber, ifscCode)
        );
    }

    // TODO: to verify address
    @PostMapping("/{sellerId}/kyc-verify/address")
    public ResponseEntity<Boolean> verifyAddress(
            @PathVariable String sellerId,
            @RequestParam String address
    ) throws IOException, InterruptedException {
        return ResponseEntity.ok(
                sellerServiceKycVerification.verifyAddress(sellerId, address)
        );
    }

}
