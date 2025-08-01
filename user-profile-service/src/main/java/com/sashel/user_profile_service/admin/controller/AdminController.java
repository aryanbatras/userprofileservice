package com.sashel.user_profile_service.admin.controller;

import com.sashel.user_profile_service.admin.dto.request.AdminRequestDtoStatus;
import com.sashel.user_profile_service.admin.dto.response.AdminResponseDto;
import com.sashel.user_profile_service.admin.dto.request.AdminRequestDto;
import com.sashel.user_profile_service.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
@RequestMapping("/api/userprofile/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @GetMapping("/admins")
    // TODO: Return list of AdminEntity objects
    public ResponseEntity<List<AdminResponseDto>> getAllAdmins() {
        return ResponseEntity.ok().body(
                adminService.getAllAdmins()
        );
    }

    @GetMapping("/{adminId}")
    // TODO: Return AdminEntity object by Id
    public ResponseEntity<AdminResponseDto> getAdmin(
            @PathVariable String adminId
    ) {
        return ResponseEntity.ok().body(
                adminService.getAdmin(adminId)
        );
    }

    @PutMapping("/{adminId}")
    // TODO: Update AdminEntity object by Id
    public ResponseEntity<AdminResponseDto> updateAdmin(
            @PathVariable String adminId,
            @RequestBody AdminRequestDto adminRequestDto
    ) {
        return ResponseEntity.ok().body(
                adminService.updateAdminName(adminId, adminRequestDto)
        );
    }

    @PutMapping("/{adminId}/status")
    // TODO: Update AdminEntity object by Id
    public ResponseEntity<AdminResponseDto> updateAdminStatus(
            @PathVariable String adminId,
            @RequestBody AdminRequestDtoStatus adminRequestDtoStatus
    ) {
        return ResponseEntity.ok().body(
                adminService.updateAdminStatus(adminId, adminRequestDtoStatus)
        );
    }

}
