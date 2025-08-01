package com.sashel.user_profile_service.seller.mapper;

import com.sashel.user_profile_service.seller.dto.request.SellerRequestDtoBrand;
import com.sashel.user_profile_service.seller.dto.response.SellerResponseDtoBrand;
import com.sashel.user_profile_service.seller.entity.SellerEntityBrand;

public class SellerMapperBrand {
    public static SellerResponseDtoBrand mapSellerEntityBrandToSellerResponseDtoBrand(SellerEntityBrand e) {
        return SellerResponseDtoBrand.builder()
                .brandLogo(e.getBrandLogo())
                .brandName(e.getBrandName())
                .brandDescription(e.getBrandDescription())
                .pincode(e.getPincode())
                .city(e.getCity())
                .state(e.getState())
                .country(e.getCountry())
                .landmark(e.getLandmark())
                .totalReviews(e.getTotalReviews())
                .avgRating(e.getAvgRating())
                .bestSellingProduct(e.getBestSellingProduct())
                .build();

    }

    public static SellerEntityBrand mapSellerRequestDtoBrandToSellerEntityBrand(SellerRequestDtoBrand sellerRequestDtoBrand, SellerEntityBrand sellerEntityBrand) {
        sellerEntityBrand.setBrandName(sellerRequestDtoBrand.getBrandName());
        sellerEntityBrand.setBrandDescription(sellerRequestDtoBrand.getBrandDescription());
        sellerEntityBrand.setPincode(sellerRequestDtoBrand.getPincode());
        sellerEntityBrand.setCity(sellerRequestDtoBrand.getCity());
        sellerEntityBrand.setState(sellerRequestDtoBrand.getState());
        sellerEntityBrand.setCountry(sellerRequestDtoBrand.getCountry());
        sellerEntityBrand.setLandmark(sellerRequestDtoBrand.getLandmark());
        sellerEntityBrand.setTotalReviews(sellerRequestDtoBrand.getTotalReviews());
        sellerEntityBrand.setAvgRating(sellerRequestDtoBrand.getAvgRating());
        return sellerEntityBrand;
    }
}
