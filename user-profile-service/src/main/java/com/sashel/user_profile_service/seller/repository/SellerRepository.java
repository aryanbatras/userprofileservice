package com.sashel.user_profile_service.seller.repository;

import com.sashel.user_profile_service.seller.entity.SellerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SellerRepository extends JpaRepository<SellerEntity, String>, JpaSpecificationExecutor<SellerEntity> {
}
