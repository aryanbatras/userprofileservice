package com.sashel.user_profile_service.admin.service;

import com.sashel.user_profile_service.admin.dto.request.AdminRequestDto;
import com.sashel.user_profile_service.admin.dto.request.AdminRequestDtoStatus;
import com.sashel.user_profile_service.admin.dto.response.AdminResponseDto;
import com.sashel.user_profile_service.admin.entity.AdminEntity;
import com.sashel.user_profile_service.admin.mapper.AdminMapper;
import com.sashel.user_profile_service.admin.repository.AdminRepository;
import com.sashel.user_profile_service.user.entity.UserEntity;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {

    @Autowired
    AdminRepository adminRepository;

    public void createAdmin(
            UserEntity userEntity, String userEmail
    ) {
        // TODO: to create admin and set email
        AdminEntity adminEntity = new AdminEntity();
        adminEntity.setUserEntity(userEntity);
        adminEntity.setEmail(userEmail);
        adminRepository.save(adminEntity);

    }

    // TODO: to get all admins data
    public List<AdminResponseDto> getAllAdmins() {
        List<AdminEntity> adminEntities = adminRepository.findAll();

        List<AdminResponseDto> adminResponseDtoList = new ArrayList<>(adminEntities.size());
        for(AdminEntity e : adminEntities) {
            adminResponseDtoList.add(
                    AdminMapper.mapAdminEntityToAdminResponseDto(e)
            );
        }

        return adminResponseDtoList;
    }

    // TODO: to get admin by Id
    public AdminResponseDto getAdmin(String adminId) {
        return adminRepository.findById(adminId)
                .map(AdminMapper::mapAdminEntityToAdminResponseDto)
                .orElseThrow(() -> new EntityNotFoundException("Admin not found"));
    }


    // TODO: to update admin name
    public AdminResponseDto updateAdminName(String adminId, AdminRequestDto adminRequestDto) {
        var adminEntity = adminRepository.findById(adminId);
        if(adminEntity.isPresent()) {
            adminEntity.get().setName(adminRequestDto.getName());
            adminRepository.save(adminEntity.get());
            return AdminMapper.mapAdminEntityToAdminResponseDto(adminEntity.get());
        } else {
            throw new EntityNotFoundException("Admin not found");
        }
    }

    // TODO: to update admin's status
    public AdminResponseDto updateAdminStatus(String adminId, AdminRequestDtoStatus adminRequestDtoStatus) {
        var adminEntity = adminRepository.findById(adminId);
        if(adminEntity.isPresent()) {
            adminEntity.get().setIsActive(adminRequestDtoStatus.getIsActive());
            adminRepository.save(adminEntity.get());
            return AdminMapper.mapAdminEntityToAdminResponseDto(adminEntity.get());
        } else {
            throw new EntityNotFoundException("Admin not found");
        }
    }
}
