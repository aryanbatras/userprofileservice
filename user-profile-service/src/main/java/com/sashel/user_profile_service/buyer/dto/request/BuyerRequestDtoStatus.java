package com.sashel.user_profile_service.buyer.dto.request;

import lombok.*;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BuyerRequestDtoStatus {
    private Boolean isActive;
}
