package com.sashel.user_profile_service.seller.controller;

import com.sashel.user_profile_service.seller.dto.request.SellerRequestDtoBrandReview;
import com.sashel.user_profile_service.seller.dto.request.SellerRequestDtoBrandStatus;
import com.sashel.user_profile_service.seller.dto.response.SellerResponseDtoBrand;
import com.sashel.user_profile_service.seller.service.SellerServiceBrand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class SellerControllerBrandTest {

    @Mock
    private SellerServiceBrand sellerServiceBrand;

    @InjectMocks
    private SellerControllerBrand sellerControllerBrand;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void reviewBrandByAdmin_ShouldReturnTrue() {
        SellerRequestDtoBrandReview review = new SellerRequestDtoBrandReview();
        when(sellerServiceBrand.reviewBrandByAdmin(anyString(), any())).thenReturn(true);

        ResponseEntity<Boolean> response = sellerControllerBrand.reviewBrandByAdmin("seller123", review);

        assertEquals(Boolean.TRUE, response.getBody( ));
        assertEquals(200, response.getStatusCode().value());
    }

    @Test
    void updateBrandStatus_ShouldReturnTrue() {
        SellerRequestDtoBrandStatus status = new SellerRequestDtoBrandStatus();
        when(sellerServiceBrand.updateBrandStatus(anyString(), any())).thenReturn(true);

        ResponseEntity<Boolean> response = sellerControllerBrand.updateBrandKycDetails("seller123", status);

        assertEquals(Boolean.TRUE, response.getBody( ));
        assertEquals(200, response.getStatusCode().value());
    }

    @Test
    void getBrandInfo_ShouldReturnBrandInfo() {
        SellerResponseDtoBrand brandInfo = new SellerResponseDtoBrand();
        when(sellerServiceBrand.getBrandInfo(anyString())).thenReturn(brandInfo);

        ResponseEntity<SellerResponseDtoBrand> response = sellerControllerBrand.getBrandInfo("seller123");
        
        assertNotNull(response.getBody());
        assertEquals(200, response.getStatusCode().value());
    }
}
