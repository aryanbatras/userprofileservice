package com.sashel.user_profile_service.admin.controller;

import com.sashel.user_profile_service.admin.dto.request.AdminRequestDto;
import com.sashel.user_profile_service.admin.dto.request.AdminRequestDtoStatus;
import com.sashel.user_profile_service.admin.dto.response.AdminResponseDto;
import com.sashel.user_profile_service.admin.service.AdminService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class AdminControllerTest {

    @Mock
    private AdminService adminService;

    @InjectMocks
    private AdminController adminController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllAdmins_ShouldReturnAdminList() {
        AdminResponseDto admin1 = new AdminResponseDto();
        AdminResponseDto admin2 = new AdminResponseDto();
        List<AdminResponseDto> admins = Arrays.asList(admin1, admin2);
        
        when(adminService.getAllAdmins()).thenReturn(admins);

        ResponseEntity<List<AdminResponseDto>> response = adminController.getAllAdmins();
        
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void getAdmin_ShouldReturnAdmin() {
        AdminResponseDto admin = new AdminResponseDto();
        when(adminService.getAdmin(anyString())).thenReturn(admin);

        ResponseEntity<AdminResponseDto> response = adminController.getAdmin("admin123");
        
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }

    @Test
    void updateAdmin_ShouldReturnUpdatedAdmin() {
        AdminRequestDto requestDto = new AdminRequestDto();
        AdminResponseDto updatedAdmin = new AdminResponseDto();
        
        when(adminService.updateAdminName(anyString(), any(AdminRequestDto.class))).thenReturn(updatedAdmin);

        ResponseEntity<AdminResponseDto> response = adminController.updateAdmin("admin123", requestDto);
        
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }
}
