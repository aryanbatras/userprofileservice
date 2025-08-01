package com.sashel.user_profile_service.seller.specification;

import com.sashel.user_profile_service.seller.entity.SellerEntity;
import org.springframework.data.jpa.domain.Specification;

import java.sql.Date;
import java.time.LocalDate;

public class SellerSpecification {

    public static Specification<SellerEntity> hasGender(String gender) {
        return (root, query, cb) -> gender == null ? null : cb.equal(root.get("gender"), gender);
    }

    public static Specification<SellerEntity> isActive(Boolean active) {
        return (root, query, cb) -> active == null ? null : cb.equal(root.get("isActive"), active);
    }

    public static Specification<SellerEntity> ageBetween(Integer minAge, Integer maxAge) {
        LocalDate today = LocalDate.now();
        Date minDob = maxAge != null ? Date.valueOf(today.minusYears(maxAge)) : null;
        Date maxDob = minAge != null ? Date.valueOf(today.minusYears(minAge)) : null;

        if (minDob == null && maxDob == null)
            return (root, query, cb) -> cb.isTrue(cb.literal(true));

        if (minDob == null)
            return (root, query, cb) -> cb.lessThanOrEqualTo(root.get("dob"), maxDob);

        if (maxDob == null)
            return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get("dob"), minDob);

        return (root, query, cb) -> cb.between(root.get("dob"), minDob, maxDob);
    }
}