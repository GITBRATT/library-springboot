package com.spring_boot_pet.repository;

import com.spring_boot_pet.model.ReaderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReaderRepository
        extends JpaRepository<ReaderEntity, Long> {
}
