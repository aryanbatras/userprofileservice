package com.sashel.user_profile_service.buyer.dto.request;

import jakarta.persistence.Column;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BuyerRequestDto {

    @Column(name = "name", length = 64)
    private String name;

    @Column(name = "dob")
    private java.sql.Date dob;

    @Column(name = "gender", length = 8)
    private String gender;

}

