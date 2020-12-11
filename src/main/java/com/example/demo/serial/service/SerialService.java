package com.example.demo.serial.service;

import com.example.demo.serial.model.Serial;
import com.example.demo.serial.repository.SerialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class SerialService {

    private final SerialRepository repository;

    public Long get(final String id) {
        Serial entity = repository.findById(id)
                .orElseGet(() -> new Serial(id, 0L));
        // Add one
        entity.setValue(entity.getValue() + 1L);
        repository.save(entity);
        return entity.getValue();
    }
}
