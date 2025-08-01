package com.sashel.user_profile_service.buyer.repository;

import com.sashel.user_profile_service.buyer.entity.BuyerEntityAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BuyerRepositoryAddress extends JpaRepository<BuyerEntityAddress, Long> {
    List<BuyerEntityAddress> findByBuyerId(String buyerId);
}
