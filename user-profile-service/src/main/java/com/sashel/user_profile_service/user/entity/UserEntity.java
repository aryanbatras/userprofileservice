package com.sashel.user_profile_service.user.entity;

import com.sashel.user_profile_service.admin.entity.AdminEntity;
import com.sashel.user_profile_service.buyer.entity.BuyerEntity;
import com.sashel.user_profile_service.seller.entity.SellerEntity;
import com.sashel.user_profile_service.user.enums.UserRole;
import jakarta.persistence.Index;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(
        name = "user",
        indexes = @Index(columnList = "user_id")
)
public class UserEntity {

    @Id @Column(name = "user_id", length = 36)
    private String userId;

    @Column(name = "user_role", nullable = false)
    private UserRole userRole;

    @Column(name = "created_at")
    private java.sql.Timestamp createdAt;

    @Column(name = "last_synced_at")
    private java.sql.Timestamp lastSyncedAt;

    @OneToOne(mappedBy = "userEntity", cascade = CascadeType.ALL)
    private BuyerEntity buyerEntity;

    @OneToOne(mappedBy = "userEntity", cascade = CascadeType.ALL)
    private SellerEntity sellerEntity;

    @OneToOne(mappedBy = "userEntity", cascade = CascadeType.ALL)
    private AdminEntity adminEntity;

}
