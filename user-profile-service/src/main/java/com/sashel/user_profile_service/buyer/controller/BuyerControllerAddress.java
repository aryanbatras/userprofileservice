package com.sashel.user_profile_service.buyer.controller;

import com.sashel.user_profile_service.buyer.dto.request.BuyerRequestDto;
import com.sashel.user_profile_service.buyer.dto.request.BuyerRequestDtoAddress;
import com.sashel.user_profile_service.buyer.dto.response.BuyerResponseDto;
import com.sashel.user_profile_service.buyer.dto.response.BuyerResponseDtoAddress;
import com.sashel.user_profile_service.buyer.service.BuyerServiceAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/userprofile/buyer")
public class BuyerControllerAddress {

    @Autowired
    BuyerServiceAddress buyerServiceAddress;

    // TODO: Return list of buyer's Addresses
    @GetMapping("{buyerId}/addresses")
    public ResponseEntity<List<BuyerResponseDtoAddress>> getBuyerAddress(
            @PathVariable String buyerId
    ) {
        return ResponseEntity.ok(
                buyerServiceAddress.getAllBuyerAddresses(buyerId)
        );
    }

    // TODO: to create buyer's address
    @PostMapping("/{buyerId}/addresses")
    public ResponseEntity<BuyerResponseDtoAddress> createBuyerAddress(
            @PathVariable String buyerId,
            @RequestBody BuyerRequestDtoAddress buyerRequestDtoAddress
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                buyerServiceAddress.createBuyerAddress(buyerId, buyerRequestDtoAddress)
        );
    }

    // TODO: to update a particular buyer's address
    @PutMapping("/{buyerId}/addresses/{addressId}")
    public ResponseEntity<BuyerResponseDtoAddress> updateBuyerAddress(
            @PathVariable String buyerId,
            @PathVariable Long addressId,
            @RequestBody BuyerRequestDtoAddress buyerRequestDtoAddress
    ) {
        return ResponseEntity.ok(
                buyerServiceAddress.updateBuyerAddress(buyerId, addressId, buyerRequestDtoAddress)
        );
    }

    // TODO: to delete a particular buyer's address
    @DeleteMapping("/{buyerId}/addresses/{addressId}")
    public ResponseEntity<?> deleteBuyerAddress(
            @PathVariable String buyerId,
            @PathVariable Long addressId
    ) {
        buyerServiceAddress.deleteBuyerAddress(buyerId, addressId);
        return ResponseEntity.ok().build();
    }
}

