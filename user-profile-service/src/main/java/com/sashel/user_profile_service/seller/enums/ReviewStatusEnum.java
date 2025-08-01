package com.sashel.user_profile_service.seller.enums;

public enum ReviewStatusEnum {
    INITIAL("INITIAL"),
    KYC_APPROVED("KYC_APPROVED"),
    IN_REVIEW_BY_ADMIN("IN_REVIEW_BY_ADMIN"),
    ADMIN_REVIEW_REJECTED("ADMIN_REVIEW_REJECTED"),
    ADMIN_REVIEW_APPROVED("ADMIN_REVIEW_APPROVED");

    private final String value;

    ReviewStatusEnum(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
