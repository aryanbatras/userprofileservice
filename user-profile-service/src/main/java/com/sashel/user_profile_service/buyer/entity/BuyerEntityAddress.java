package com.sashel.user_profile_service.buyer.entity;

import com.sashel.user_profile_service.buyer.enums.AddressTypeEnum;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Builder
public class BuyerEntityAddress {

    @ManyToOne @JoinColumn(name = "buyer_id", nullable = false)
    private BuyerEntity buyerEntity;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    @Column(length = 36, nullable = false)
    private String buyerId;

    private Integer pincode;

    private String city;

    private String state;

    private String country;

    private String landmark;

    @Enumerated(EnumType.STRING)
    private AddressTypeEnum addressType;

    @Column(columnDefinition = "boolean default false")
    private Boolean isPrimary;
}
