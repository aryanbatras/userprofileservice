package com.sashel.user_profile_service.buyer.dto.response;

import com.sashel.user_profile_service.buyer.enums.AddressTypeEnum;
import lombok.*;

@Data
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BuyerResponseDtoAddress {
    private Long addressId;
    private Integer pincode;
    private String city;
    private String state;
    private String country;
    private String landmark;
    private AddressTypeEnum addressType;
    private Boolean isPrimary;
}
