package com.sashel.user_profile_service.seller.controller;

import com.sashel.user_profile_service.seller.dto.response.SellerResponseDtoKycVerify;
import com.sashel.user_profile_service.seller.service.SellerServiceKycVerification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class SellerControllerKycVerificationTest {

    @Mock
    private SellerServiceKycVerification sellerServiceKycVerification;

    @InjectMocks
    private SellerControllerKycVerification sellerControllerKycVerification;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getBrandKycDetails_ShouldReturnKycDetails() {
        SellerResponseDtoKycVerify kycDetails = new SellerResponseDtoKycVerify();
        when(sellerServiceKycVerification.getBrandKycDetails(anyString())).thenReturn(kycDetails);

        ResponseEntity<SellerResponseDtoKycVerify> response = 
            sellerControllerKycVerification.getBrandKycDetails("seller123");
        
        assertNotNull(response.getBody());
        assertEquals(200, response.getStatusCode().value());
    }

    @Test
    void verifyPan_ShouldReturnTrue() throws Exception {
        when(sellerServiceKycVerification.verifyPan(anyString(), anyString())).thenReturn(true);

        ResponseEntity<Boolean> response = 
            sellerControllerKycVerification.verifyPan("seller123", "ABCDE1234F");

        assertEquals(Boolean.TRUE, response.getBody( ));
        assertEquals(200, response.getStatusCode().value());
    }

    @Test
    void verifyAadhar_ShouldReturnTrue() throws Exception {
        when(sellerServiceKycVerification.verifyAadhar(anyString(), anyString())).thenReturn(true);

        ResponseEntity<Boolean> response = 
            sellerControllerKycVerification.verifyAadhar("seller123", "123456789012");

        assertEquals(Boolean.TRUE, response.getBody( ));
        assertEquals(200, response.getStatusCode().value());
    }

    @Test
    void verifyGst_ShouldReturnTrue() throws Exception {
        when(sellerServiceKycVerification.verifyGst(anyString(), anyString())).thenReturn(true);

        ResponseEntity<Boolean> response = 
            sellerControllerKycVerification.verifyGst("seller123", "22ABCDE1234F1Z5");

        assertEquals(Boolean.TRUE, response.getBody( ));
        assertEquals(200, response.getStatusCode().value());
    }
}
