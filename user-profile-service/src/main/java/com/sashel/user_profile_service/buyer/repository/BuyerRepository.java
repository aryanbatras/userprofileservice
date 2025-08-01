package com.sashel.user_profile_service.buyer.repository;

import com.sashel.user_profile_service.buyer.entity.BuyerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

@Repository
public interface BuyerRepository extends JpaRepository<BuyerEntity, String>, JpaSpecificationExecutor<BuyerEntity> {
    Page<BuyerEntity> findAll(Pageable pageable);
}
