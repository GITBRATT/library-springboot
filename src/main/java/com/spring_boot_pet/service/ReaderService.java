package com.spring_boot_pet.service;

import com.spring_boot_pet.model.ReaderEntity;
import com.spring_boot_pet.repository.ReaderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReaderService {
    private final ReaderRepository readerRepository;

    public ReaderService(ReaderRepository readerRepository) {
        this.readerRepository = readerRepository;
    }

    public List<ReaderEntity> findAll() {
        return readerRepository.findAll();
    }

    public ReaderEntity findById(Long id) {
        return readerRepository.findById(id).orElseThrow( () ->
                new RuntimeException("Reader not found")
        );
    }

    public void deleteById(Long id) {
        readerRepository.deleteById(id);
    }

    public ReaderEntity save(ReaderEntity reader) {
        return readerRepository.save(reader);
    }

}
