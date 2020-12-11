package com.example.demo.store.repository;

import com.example.demo.book.entity.BookDTO;

import java.util.List;

public interface QStoreRepository {

    void deleteUnrelatedBooks(Long id, List<BookDTO> books);
}
