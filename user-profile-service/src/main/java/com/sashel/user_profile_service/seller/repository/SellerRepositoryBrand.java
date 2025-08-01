package com.sashel.user_profile_service.seller.repository;

import com.sashel.user_profile_service.seller.entity.SellerEntityBrand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepositoryBrand extends JpaRepository<SellerEntityBrand, String> {
}
