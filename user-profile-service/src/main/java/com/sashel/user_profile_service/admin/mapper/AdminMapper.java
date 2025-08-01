package com.sashel.user_profile_service.admin.mapper;

import com.sashel.user_profile_service.admin.dto.response.AdminResponseDto;
import com.sashel.user_profile_service.admin.entity.AdminEntity;

public class AdminMapper {
    public static AdminResponseDto mapAdminEntityToAdminResponseDto(AdminEntity e) {
        return AdminResponseDto.builder()
                .name(e.getName())
                .email(e.getEmail())
                .isActive(e.getIsActive())
                .lastLoginAt(e.getLastLoginAt())
                .build();
    }
}
