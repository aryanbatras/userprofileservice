package com.sashel.user_profile_service.seller.entity;

import com.sashel.user_profile_service.user.entity.UserEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Setter;
import lombok.Getter;

@Entity
@Getter
@Setter
@Table(
        name = "seller",
        indexes = @Index(columnList = "sellerId")
)
public class SellerEntity {

    @Id
    @Column(name = "seller_id", length = 36)
    private String sellerId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "seller_id")
    private UserEntity userEntity;

    @Column(length = 64)
    private String name;

    private java.sql.Date dob;

    @Column(length = 8)
    private String gender;

    @Column(nullable = false, unique = true, length = 128)
    private String email;

    @Column(unique = true, length = 26)
    private String phone;

    @Column(length = 255)
    private String profileImage;

    private Integer pincode;

    @Column(length = 64)
    private String city;

    @Column(length = 64)
    private String state;

    @Column(length = 64)
    private String country;

    @Column(length = 64)
    private String landmark;

    @Column(name = "is_active", columnDefinition = "boolean default true")
    private Boolean isActive;

    @OneToOne(mappedBy = "sellerEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private SellerEntityBrand sellerEntityBrand;
    
    @PrePersist
    @PreUpdate
    private void updateSellerId() {
        if (userEntity != null) {
            this.sellerId = userEntity.getUserId();
        }
    }
}
