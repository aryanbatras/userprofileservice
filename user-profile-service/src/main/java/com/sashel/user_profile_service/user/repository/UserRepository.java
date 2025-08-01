package com.sashel.user_profile_service.user.repository;

import com.sashel.user_profile_service.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String> {
}
