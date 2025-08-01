package com.sashel.user_profile_service.buyer.controller;

import com.sashel.user_profile_service.buyer.dto.request.BuyerRequestDtoAddress;
import com.sashel.user_profile_service.buyer.dto.response.BuyerResponseDtoAddress;
import com.sashel.user_profile_service.buyer.service.BuyerServiceAddress;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class BuyerControllerAddressTest {

    @Mock
    private BuyerServiceAddress buyerServiceAddress;

    @InjectMocks
    private BuyerControllerAddress buyerControllerAddress;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getBuyerAddress_ShouldReturnAddressList() {
        BuyerResponseDtoAddress address1 = new BuyerResponseDtoAddress();
        BuyerResponseDtoAddress address2 = new BuyerResponseDtoAddress();
        List<BuyerResponseDtoAddress> addresses = Arrays.asList(address1, address2);
        
        when(buyerServiceAddress.getAllBuyerAddresses(anyString())).thenReturn(addresses);

        ResponseEntity<List<BuyerResponseDtoAddress>> response = 
            buyerControllerAddress.getBuyerAddress("buyer123");
        
        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void createBuyerAddress_ShouldReturnCreatedAddress() {
        BuyerRequestDtoAddress requestDto = new BuyerRequestDtoAddress();
        BuyerResponseDtoAddress responseDto = new BuyerResponseDtoAddress();
        
        when(buyerServiceAddress.createBuyerAddress(anyString(), any(BuyerRequestDtoAddress.class)))
            .thenReturn(responseDto);

        ResponseEntity<BuyerResponseDtoAddress> response = 
            buyerControllerAddress.createBuyerAddress("buyer123", requestDto);
        
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void updateBuyerAddress_ShouldReturnUpdatedAddress() {
        BuyerRequestDtoAddress requestDto = new BuyerRequestDtoAddress();
        BuyerResponseDtoAddress responseDto = new BuyerResponseDtoAddress();
        
        when(buyerServiceAddress.updateBuyerAddress(anyString(), anyLong(), any(BuyerRequestDtoAddress.class)))
            .thenReturn(responseDto);

        ResponseEntity<BuyerResponseDtoAddress> response = 
            buyerControllerAddress.updateBuyerAddress("buyer123", 1L, requestDto);
        
        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
    }
}
