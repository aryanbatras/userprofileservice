package com.sashel.user_profile_service.buyer.dto.request;


import com.sashel.user_profile_service.buyer.enums.AddressTypeEnum;
import lombok.*;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BuyerRequestDtoAddress {
    private Integer pincode;
    private String city;
    private String state;
    private String country;
    private String landmark;
    private AddressTypeEnum addressType;
}

