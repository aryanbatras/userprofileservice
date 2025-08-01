package com.sashel.user_profile_service.seller.dto.request;

import lombok.*;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SellerRequestDtoKyc {
    private String brandName;
    private String brandDescription;
    private Integer pincode;
    private String city;
    private String state;
    private String country;
    private String landmark;
    private Integer totalReviews;
    private Float avgRating;
    private String contactPhone;
    private String emergencyContact;
    private String panNumber;
    private String aadhaarNumber;
    private String gstNumber;
    private String bankAccountNumber;
    private String ifscCode;
    private String pickupAddress;
}
