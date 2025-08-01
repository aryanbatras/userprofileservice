package com.sashel.user_profile_service.seller.mapper;

import com.sashel.user_profile_service.seller.dto.request.SellerRequestDtoKyc;
import com.sashel.user_profile_service.seller.dto.response.SellerResponseDtoKycVerify;
import com.sashel.user_profile_service.seller.entity.SellerEntityBrand;

public class SellerMapperKycVerify {
    public static SellerEntityBrand mapSellerRequestDtoKycVerifyToSellerEntityBrand(SellerRequestDtoKyc sellerRequestDtoKyc, SellerEntityBrand sellerEntityBrand) {
        sellerEntityBrand.setBrandName(sellerRequestDtoKyc.getBrandName());
        sellerEntityBrand.setBrandDescription(sellerRequestDtoKyc.getBrandDescription());
        sellerEntityBrand.setPincode(sellerRequestDtoKyc.getPincode());
        sellerEntityBrand.setCity(sellerRequestDtoKyc.getCity());
        sellerEntityBrand.setState(sellerRequestDtoKyc.getState());
        sellerEntityBrand.setCountry(sellerRequestDtoKyc.getCountry());
        sellerEntityBrand.setLandmark(sellerRequestDtoKyc.getLandmark());
        sellerEntityBrand.setTotalReviews(sellerRequestDtoKyc.getTotalReviews());
        sellerEntityBrand.setAvgRating(sellerRequestDtoKyc.getAvgRating());
        sellerEntityBrand.setContactPhone(sellerRequestDtoKyc.getContactPhone());
        sellerEntityBrand.setEmergencyContact(sellerRequestDtoKyc.getEmergencyContact());
        return sellerEntityBrand;
    }

    public static SellerResponseDtoKycVerify mapSellerEntityBrandToSellerResponseDtoKycVerify(SellerEntityBrand e) {
        return SellerResponseDtoKycVerify.builder()
                .panVerified(e.getPanVerified())
                .gstVerified(e.getGstVerified())
                .bankVerified(e.getBankVerified())
                .aadhaarVerified(e.getAadhaarVerified())
                .addressVerified(e.getAddressVerified())
                .build();
    }
}
