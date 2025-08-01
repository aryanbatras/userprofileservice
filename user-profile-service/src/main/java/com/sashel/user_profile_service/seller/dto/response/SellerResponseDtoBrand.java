package com.sashel.user_profile_service.seller.dto.response;

import com.sashel.user_profile_service.seller.entity.SellerEntity;
import com.sashel.user_profile_service.seller.enums.ReviewStatusEnum;
import jakarta.persistence.*;
import lombok.*;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SellerResponseDtoBrand {
    private String brandLogo;
    private String brandName;
    private String brandDescription;
    private Integer pincode;
    private String city;
    private String state;
    private String country;
    private String landmark;
    private Integer totalReviews;
    private Float avgRating;
    private Integer bestSellingProduct;
}
