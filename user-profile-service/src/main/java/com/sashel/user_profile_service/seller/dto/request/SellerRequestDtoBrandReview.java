package com.sashel.user_profile_service.seller.dto.request;

import lombok.*;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SellerRequestDtoBrandReview {
    private Boolean isReviewed;
    private String reviewComments;
    private String reviewStatus;
}
