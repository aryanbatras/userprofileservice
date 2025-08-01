package com.sashel.user_profile_service.seller.service;

import com.sashel.user_profile_service.seller.dto.response.SellerResponseDtoKycVerify;
import com.sashel.user_profile_service.seller.mapper.SellerMapperKycVerify;
import com.sashel.user_profile_service.seller.repository.SellerRepositoryBrand;
import com.sashel.user_profile_service.utility.service.AuthBridgeService;
import com.sashel.user_profile_service.utility.service.SnsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SellerServiceKycVerification {

    @Autowired
    SellerRepositoryBrand sellerRepositoryBrand;

    @Autowired
    AuthBridgeService authBridgeService;

    @Autowired
    SnsService snsService;

    // TODO: to verify seller's pan number
    public boolean verifyPan(String sellerId, String value) throws IOException, InterruptedException {
        if(authBridgeService.verifyPan(value)) {
            var sellerEntityBrand = sellerRepositoryBrand.findById(sellerId);
            if(sellerEntityBrand.isPresent()) {
                sellerEntityBrand.get().setPanVerified(true);
                sellerRepositoryBrand.save(sellerEntityBrand.get());
            }
            return true;
        } else {
            return false;
        }
    }

    // TODO: to verify seller's aadhar card number
    public Boolean verifyAadhar(String sellerId, String value) throws IOException, InterruptedException {
        if(authBridgeService.verifyAadhaar(value)) {
            var sellerEntityBrand = sellerRepositoryBrand.findById(sellerId);
            if(sellerEntityBrand.isPresent()) {
                sellerEntityBrand.get().setAadhaarVerified(true);
                sellerRepositoryBrand.save(sellerEntityBrand.get());
            }
            return true;
        } else {
            return false;
        }
    }


   // TODO: to verify seller's gst
    public Boolean verifyGst(String sellerId, String value) throws IOException, InterruptedException {
        if(authBridgeService.verifyGst(value)) {
            var sellerEntityBrand = sellerRepositoryBrand.findById(sellerId);
            if(sellerEntityBrand.isPresent()) {
                sellerEntityBrand.get().setGstVerified(true);
                sellerRepositoryBrand.save(sellerEntityBrand.get());
            }
            return true;
        } else {
            return false;
        }
    }

    // TODO: to verify seller's bank
    public Boolean verifyBank(String sellerId, String accountNumber, String ifscCode) throws IOException, InterruptedException {
        if(authBridgeService.verifyBank(accountNumber, ifscCode)) {
            var sellerEntityBrand = sellerRepositoryBrand.findById(sellerId);
            if(sellerEntityBrand.isPresent()) {
                sellerEntityBrand.get().setBankVerified(true);
                sellerRepositoryBrand.save(sellerEntityBrand.get());
            }
            return true;
        } else {
            return false;
        }
    }

    // TODO: to verify seller's address
    public Boolean verifyAddress(String sellerId, String address) throws IOException, InterruptedException {
        if(authBridgeService.verifyAddress(address)) {
            var sellerEntityBrand = sellerRepositoryBrand.findById(sellerId);
            if(sellerEntityBrand.isPresent()) {
                sellerEntityBrand.get().setAddressVerified(true);
                sellerRepositoryBrand.save(sellerEntityBrand.get());
            }
            return true;
        } else {
            return false;
        }

    }

    // TODO: to get brand's kyc details
    public SellerResponseDtoKycVerify getBrandKycDetails(String sellerId) {
        var sellerEntityBrand = sellerRepositoryBrand.findById(sellerId);
        return sellerEntityBrand.map(SellerMapperKycVerify::mapSellerEntityBrandToSellerResponseDtoKycVerify).orElse(null);
    }
}


