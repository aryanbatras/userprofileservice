package com.sashel.user_profile_service.user.controller;

import com.sashel.user_profile_service.user.dto.UserResponseDto;
import com.sashel.user_profile_service.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void signup_ShouldReturnUserResponse() {
        UserResponseDto mockResponse = new UserResponseDto();
        when(userService.signup(anyString(), anyString(), anyString(), anyString()))
                .thenReturn(mockResponse);

        ResponseEntity<UserResponseDto> response = userController.signup(
                "firebase123", "BUYER", "test@example.com", "+1234567890");

        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
    }
}
