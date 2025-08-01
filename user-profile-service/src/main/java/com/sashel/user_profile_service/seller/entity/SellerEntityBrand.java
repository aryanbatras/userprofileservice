package com.sashel.user_profile_service.seller.entity;

import com.sashel.user_profile_service.seller.enums.ReviewStatusEnum;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SellerEntityBrand {

    @Id
    @Column(name = "brand_id", length = 36, nullable = false)
    private String brandId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "brand_id")
    private SellerEntity sellerEntity;

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

    private String contactPhone;

    private String emergencyContact;

    @Column(name = "is_pan_verified",
            columnDefinition = "boolean default false")
    private Boolean panVerified;

    @Column(name = "is_gst_verified",
            columnDefinition = "boolean default false")
    private Boolean gstVerified;

    @Column(name = "is_aadhaar_verified",
            columnDefinition = "boolean default false")
    private Boolean aadhaarVerified;

    @Column(name = "is_bank_verified",
            columnDefinition = "boolean default false")
    private Boolean bankVerified;

    @Column(name = "is_address_proof_verified",
            columnDefinition = "boolean default false")
    private Boolean addressVerified;

    private String reviewComments;

    private Boolean isReviewed;
    
    @PrePersist
    @PreUpdate
    private void updateBrandId() {
        if (sellerEntity != null) {
            this.brandId = sellerEntity.getSellerId();
        }
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "review_status")
    private ReviewStatusEnum reviewStatus;

    private Integer bestSellingProduct;
}
