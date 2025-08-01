package com.sashel.user_profile_service.admin.entity;

import com.sashel.user_profile_service.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(
        name = "admin",
        indexes = @Index(columnList = "admin_id")
)
public class AdminEntity {

    @Id
    @Column(name = "admin_id", length = 36)
    private String adminId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "admin_id")
    private UserEntity userEntity;

    @Column(name = "name", length = 64)
    private String name;

    @Column(name = "email", nullable = false, length = 128)
    private String email;

    @Column(name = "is_active",
            columnDefinition = "boolean default true")
    private Boolean isActive;

    @Column(name = "last_login_at")
    private java.sql.Date lastLoginAt;
    
    @PrePersist
    @PreUpdate
    private void updateAdminId() {
        if (userEntity != null) {
            this.adminId = userEntity.getUserId();
        }
    }
}
