package com.example.demo.book.controller;

import com.example.demo.book.entity.BookDTO;
import com.example.demo.book.exception.BookNotFoundException;
import com.example.demo.book.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService service;

    @GetMapping("/api/book")
    public List<BookDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/api/book/{id}")
    public BookDTO findById(@PathVariable Long id) throws BookNotFoundException {
        return service.findById(id);
    }

    @PostMapping("/api/book")
    public BookDTO create(@RequestBody @Valid BookDTO dto) throws BookNotFoundException {
        return service.save(null, dto);
    }

    @SneakyThrows
    @PostMapping("/api/book/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public BookDTO update(@PathVariable Long id, @RequestBody @Valid BookDTO dto) {
        return service.save(id, dto);
    }

    @DeleteMapping("/api/book/{id}")
    public void deleteById(@PathVariable Long id) throws BookNotFoundException {
        service.deleteById(id);
    }
}
