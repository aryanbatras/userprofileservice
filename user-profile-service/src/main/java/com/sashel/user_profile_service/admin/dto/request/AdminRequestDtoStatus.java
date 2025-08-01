package com.sashel.user_profile_service.admin.dto.request;

import lombok.*;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminRequestDtoStatus {
    private Boolean isActive;
}
