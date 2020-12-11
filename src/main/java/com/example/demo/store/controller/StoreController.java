package com.example.demo.store.controller;

import com.example.demo.store.exception.StoreNotFoundException;
import com.example.demo.store.model.StoreDTO;
import com.example.demo.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class StoreController {

    private final StoreService service;

    @GetMapping("/api/store")
    public List<StoreDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/api/store/{id}")
    public StoreDTO findById(@PathVariable Long id) throws StoreNotFoundException {
        return service.findById(id);
    }

    @PostMapping("/api/store")
    @ResponseStatus(HttpStatus.CREATED)
    public StoreDTO create(@RequestBody @Valid StoreDTO dto) throws StoreNotFoundException {
        return service.save(null, dto);
    }

    @PostMapping("/api/store/{id}")
    public StoreDTO update(@PathVariable Long id, @RequestBody @Valid StoreDTO dto) throws StoreNotFoundException {
        return service.save(id, dto);
    }

    @DeleteMapping("/api/store/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }
}
