package com.sashel.user_profile_service.seller.controller;

import com.sashel.user_profile_service.seller.dto.response.SellerResponseDto;
import com.sashel.user_profile_service.seller.entity.SellerEntity;
import com.sashel.user_profile_service.seller.service.SellerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class SellerControllerTest {

    @Mock
    private SellerService sellerService;

    @InjectMocks
    private SellerController sellerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getSellers_ShouldReturnPaginatedSellers() {
        Page<SellerEntity> page = new PageImpl<>(List.of(new SellerEntity()));
        when(sellerService.getAllSellersWithFilters(
                any(), any(), any(), any(), any(PageRequest.class)))
                .thenReturn(page);

        ResponseEntity<Page<SellerEntity>> response = sellerController.getSellers(
                0, 10, "MALE", true, 20, 30);

        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getTotalElements());
    }

    @Test
    void getSeller_ShouldReturnSeller() {
        SellerResponseDto seller = new SellerResponseDto();
        when(sellerService.getSeller(anyString())).thenReturn(seller);

        ResponseEntity<SellerResponseDto> response = sellerController.getSeller("seller123");
        
        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
    }
}
