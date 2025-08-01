package com.sashel.user_profile_service.seller.dto.request;


import lombok.*;

import java.time.LocalDate;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SellerRequestDto {
    private String name;
    private LocalDate dob;
    private String gender;
    private String phone;
    private Integer pincode;
    private String city;
    private String state;
    private String country;
    private String landmark;
}
