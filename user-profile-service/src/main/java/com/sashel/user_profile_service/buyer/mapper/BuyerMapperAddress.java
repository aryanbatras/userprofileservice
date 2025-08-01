package com.sashel.user_profile_service.buyer.mapper;

import com.sashel.user_profile_service.buyer.dto.request.BuyerRequestDtoAddress;
import com.sashel.user_profile_service.buyer.dto.response.BuyerResponseDtoAddress;
import com.sashel.user_profile_service.buyer.entity.BuyerEntityAddress;

public class BuyerMapperAddress {
    public static BuyerResponseDtoAddress mapBuyerEntityAddressToBuyerResponseDtoAddress(BuyerEntityAddress e) {
        return BuyerResponseDtoAddress.builder()
                .addressId(e.getAddressId())
                .pincode(e.getPincode())
                .city(e.getCity())
                .state(e.getState())
                .country(e.getCountry())
                .landmark(e.getLandmark())
                .addressType(e.getAddressType())
                .isPrimary(e.getIsPrimary())
                .build();
    }

    public static BuyerEntityAddress mapBuyerRequestDtoAddressToBuyerEntityAddress(BuyerRequestDtoAddress buyerRequestDtoAddress) {
        return BuyerEntityAddress.builder()
                .pincode(buyerRequestDtoAddress.getPincode())
                .city(buyerRequestDtoAddress.getCity())
                .state(buyerRequestDtoAddress.getState())
                .country(buyerRequestDtoAddress.getCountry())
                .landmark(buyerRequestDtoAddress.getLandmark())
                .addressType(buyerRequestDtoAddress.getAddressType())
                .build();
    }

    public static BuyerEntityAddress mapBuyerEntityAddressWithBuyerRequestDtoAddress(BuyerEntityAddress buyerEntityAddress, BuyerRequestDtoAddress buyerRequestDtoAddress) {
        buyerEntityAddress.setCity(buyerRequestDtoAddress.getCity());
        buyerEntityAddress.setState(buyerRequestDtoAddress.getState());
        buyerEntityAddress.setPincode(buyerRequestDtoAddress.getPincode());
        buyerEntityAddress.setCountry(buyerRequestDtoAddress.getCountry());
        buyerEntityAddress.setLandmark(buyerRequestDtoAddress.getLandmark());
        buyerEntityAddress.setAddressType(buyerRequestDtoAddress.getAddressType());
        return buyerEntityAddress;
    }
}
