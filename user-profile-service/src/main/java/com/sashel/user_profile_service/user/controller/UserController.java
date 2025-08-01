package com.sashel.user_profile_service.user.controller;

import com.sashel.user_profile_service.user.dto.UserResponseDto;
import com.sashel.user_profile_service.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/userprofile/" + "user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> signup(
            @RequestHeader("X-Firebase-UUID") String firebaseUUID,
            @RequestHeader("X-User-Role") String userRole,
            @RequestHeader("X-User-Email") String userEmail,
            @RequestHeader("X-User-Phone") String userPhone
    ) {
        return ResponseEntity.ok(
                userService.signup(
                        firebaseUUID,
                        userRole,
                        userEmail,
                        userPhone
                )
        );
    }
}
