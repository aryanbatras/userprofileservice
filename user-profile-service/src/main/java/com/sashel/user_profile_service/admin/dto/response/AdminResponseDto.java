package com.sashel.user_profile_service.admin.dto.response;

import lombok.*;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminResponseDto {
    private String adminId;
    private String name;
    private String email;
    private Boolean isActive;
    private java.sql.Date lastLoginAt;
}


