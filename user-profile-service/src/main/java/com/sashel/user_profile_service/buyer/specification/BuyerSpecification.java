package com.sashel.user_profile_service.buyer.specification;

import com.sashel.user_profile_service.buyer.entity.BuyerEntity;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.sql.Date;

public class BuyerSpecification {

    public static Specification<BuyerEntity> hasGender(String gender) {
        return (root, query, cb) -> gender == null ? null : cb.equal(root.get("gender"), gender);
    }

    public static Specification<BuyerEntity> isActive(Boolean active) {
        return (root, query, cb) -> active == null ? null : cb.equal(root.get("isActive"), active);
    }

    public static Specification<BuyerEntity> ageBetween(Integer minAge, Integer maxAge) {
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