package com.sashel.user_profile_service.buyer.mapper;

import com.sashel.user_profile_service.buyer.dto.response.BuyerResponseDto;
import com.sashel.user_profile_service.buyer.entity.BuyerEntity;

public class BuyerMapper {
    public static BuyerResponseDto mapBuyerEntityToBuyerResponseDto(BuyerEntity e) {
        return BuyerResponseDto.builder()
                .buyerId(e.getUserEntity().getUserId())
                .name(e.getName())
                .phone(e.getPhone())
                .dob(e.getDob().toLocalDate( ))
                .gender(e.getGender())
                .isActive(e.getIsActive())
                .build();
    }
}
