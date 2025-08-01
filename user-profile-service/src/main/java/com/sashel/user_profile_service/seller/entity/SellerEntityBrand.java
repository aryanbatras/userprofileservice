package com.sashel.user_profile_service.seller.entity;

import com.sashel.user_profile_service.seller.enums.ReviewStatusEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SellerEntityBrand {

    @OneToOne @MapsId @JoinColumn(name = "brand_id")
    private SellerEntity sellerEntity;

    @Id @Column(name = "brand_id", length = 36, nullable = false)
    private String brandId;

    @Column(name = "brand_logo", length = 255)
    private String brandLogo;

    @Column(name = "brand_name", length = 255)
    private String brandName;

    @Column(name = "brand_description")
    private String brandDescription;

    private Integer pincode;

    @Column(length = 64)
    private String city;

    @Column(length = 64)
    private String state;

    @Column(length = 64)
    private String country;

    @Column(length = 64)
    private String landmark;

    private Integer totalReviews;

    private Float avgRating;

    @Column(name = "kyc_verified",
            columnDefinition = "boolean default false")
    private Boolean kycVerified;

    @Enumerated(EnumType.STRING)
    @Column(name = "review_status")
    private ReviewStatusEnum reviewStatus;

    private Integer bestSellingProduct;
}
