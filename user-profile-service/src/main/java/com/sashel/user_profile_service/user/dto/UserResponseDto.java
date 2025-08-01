package com.sashel.user_profile_service.user.dto;

import com.sashel.user_profile_service.user.enums.UserRole;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    @Column(nullable = false, length = 36)
    private String userId;
    @Column(nullable = false)
    private UserRole userRole;
}
