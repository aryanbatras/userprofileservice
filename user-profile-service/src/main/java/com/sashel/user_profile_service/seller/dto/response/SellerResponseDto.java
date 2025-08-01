package com.sashel.user_profile_service.seller.dto.response;

import lombok.*;

import java.time.LocalDate;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SellerResponseDto {
    private String name;
    private LocalDate dob;
    private String gender;
    private String email;
    private String phone;
    private String profileImage;
    private Integer pincode;
    private String city;
    private String state;
    private String country;
    private String landmark;
    private Boolean isActive;
}
