package com.spring_boot_pet.repository;

import com.spring_boot_pet.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository
        extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
}