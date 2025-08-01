package com.sashel.user_profile_service.buyer.entity;

import com.sashel.user_profile_service.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "buyer")
public class BuyerEntity {

    @Id
    @Column(name = "buyer_id", length = 36)
    private String buyerId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "buyer_id")
    private UserEntity userEntity;

    @Column(name = "name", length = 64)
    private String name;

    @Column(name = "dob")
    private java.sql.Date dob;

    @Column(name = "phone", unique = true, nullable = false, length = 26)
    private String phone;

    @Column(name = "gender", length = 8)
    private String gender;

    @Column(name = "is_active",
            columnDefinition = "boolean default true")
    private Boolean isActive;

    @OneToMany(mappedBy = "buyerEntity", cascade = CascadeType.ALL)
    private List<BuyerEntityAddress> buyerAddressEntity;
    
    @PrePersist
    @PreUpdate
    private void updateBuyerId() {
        if (userEntity != null) {
            this.buyerId = userEntity.getUserId();
        }
    }
}
