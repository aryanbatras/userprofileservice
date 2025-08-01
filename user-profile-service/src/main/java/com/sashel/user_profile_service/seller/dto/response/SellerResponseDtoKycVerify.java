package com.sashel.user_profile_service.seller.dto.response;

import lombok.*;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SellerResponseDtoKycVerify {
    private Boolean panVerified;
    private Boolean gstVerified;
    private Boolean aadhaarVerified;
    private Boolean bankVerified;
    private Boolean addressVerified;
}