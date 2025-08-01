package com.sashel.user_profile_service.buyer.controller;

import com.sashel.user_profile_service.buyer.dto.response.BuyerResponseDto;
import com.sashel.user_profile_service.buyer.entity.BuyerEntity;
import com.sashel.user_profile_service.buyer.service.BuyerService;
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

class BuyerControllerTest {

    @Mock
    private BuyerService buyerService;

    @InjectMocks
    private BuyerController buyerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getBuyers_ShouldReturnPaginatedBuyers() {
        Page<BuyerEntity> page = new PageImpl<>(List.of(new BuyerEntity()));
        when(buyerService.getAllBuyersWithFilters(
                any(), any(), any(), any(), any(PageRequest.class)))
                .thenReturn(page);

        ResponseEntity<Page<BuyerEntity>> response = buyerController.getBuyers(
                0, 10, "MALE", true, 20, 30);

        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getTotalElements());
    }

    @Test
    void getBuyer_ShouldReturnBuyer() {
        BuyerResponseDto buyer = new BuyerResponseDto();
        when(buyerService.getBuyer(anyString())).thenReturn(buyer);

        ResponseEntity<BuyerResponseDto> response = buyerController.getBuyer("buyer123");
        
        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
    }
}
