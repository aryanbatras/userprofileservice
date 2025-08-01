package com.sashel.user_profile_service.user.service;

import com.sashel.user_profile_service.admin.service.AdminService;
import com.sashel.user_profile_service.buyer.service.BuyerService;
import com.sashel.user_profile_service.seller.service.SellerService;
import com.sashel.user_profile_service.user.entity.UserEntity;
import com.sashel.user_profile_service.user.enums.UserRole;
import com.sashel.user_profile_service.user.repository.UserRepository;
import com.sashel.user_profile_service.user.dto.UserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired UserRepository userRepository;

    @Autowired SellerService sellerService;
    @Autowired BuyerService buyerService;
    @Autowired AdminService adminService;

    // TODO: to check firebase uuid exists in db or not
    // TODO: if it exists then return ok response
    // TODO: else create new user with firebase uuid and role
    //          and then return the same id plus ok response
    public UserResponseDto signup(
            String firebaseUUID,
            String userRole,
            String userEmail,
            String userPhone
    ) {

        var userEntityByFirebaseUUID =
                userRepository.findByFirebaseUUID(firebaseUUID);

        // if firebase uuid exists then return ok response
        if (userEntityByFirebaseUUID.isPresent()) {
            var userEntity = userEntityByFirebaseUUID.get();
            userEntity.setLastSyncedAt(new java.sql.Timestamp(System.currentTimeMillis( )));
            var savedUserEntity = userRepository.save(userEntity);
            return new UserResponseDto(
                    savedUserEntity.getUserId(),
                    savedUserEntity.getUserRole()
            );
        }

        // if firebase uuid doesn't exist then create new user
        else {
            var userEntity = new UserEntity();
            userEntity.setUserId(firebaseUUID);
            userEntity.setUserRole(UserRole.valueOf(userRole));
            userEntity.setCreatedAt(new java.sql.Timestamp(System.currentTimeMillis( )));
            userEntity.setLastSyncedAt(new java.sql.Timestamp(System.currentTimeMillis( )));
            var savedUserEntity = userRepository.save(userEntity);

            // based on user role go to respective service
            switch (savedUserEntity.getUserRole()) {
                case SELLER:
                    // TODO: call seller service
                    sellerService.createSeller(
                            savedUserEntity, // for mapping
                            userEmail
                    );
                    break;
                case BUYER:
                    // TODO: call buyer service
                    buyerService.createBuyer(
                            savedUserEntity, // for mapping
                            userPhone
                    );
                    break;
                case ADMIN:
                    // TODO: call admin service
                    adminService.createAdmin(
                            savedUserEntity, // for mapping
                            userEmail
                    );
                    break;
            }

            return new UserResponseDto(
                    savedUserEntity.getUserId(),
                    savedUserEntity.getUserRole()
            );
        }
    }
}