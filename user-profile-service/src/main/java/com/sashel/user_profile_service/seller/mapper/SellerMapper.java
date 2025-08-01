package com.sashel.user_profile_service.seller.mapper;

import com.sashel.user_profile_service.seller.dto.response.SellerResponseDto;
import com.sashel.user_profile_service.seller.entity.SellerEntity;

public class SellerMapper {
    public static SellerResponseDto mapSellerEntityToSellerResponseDto(SellerEntity e) {
        return SellerResponseDto.builder()
                .name(e.getName())
                .dob(e.getDob().toLocalDate( ))
                .gender(e.getGender())
                .email(e.getEmail())
                .phone(e.getPhone())
                .profileImage(e.getProfileImage())
                .pincode(e.getPincode())
                .city(e.getCity())
                .state(e.getState())
                .country(e.getCountry())
                .landmark(e.getLandmark())
                .isActive(e.getIsActive())
                .build();
    }
}
