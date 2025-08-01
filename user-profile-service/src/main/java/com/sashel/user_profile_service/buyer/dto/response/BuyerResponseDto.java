package com.sashel.user_profile_service.buyer.dto.response;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BuyerResponseDto {
    private String buyerId;
    private String name;
    private LocalDate dob;
    private String phone;
    private String gender;
    private Boolean isActive;
}
