package com.example.demo.store.service;

import com.example.demo.book.entity.Book;
import com.example.demo.book.entity.BookDTO;
import com.example.demo.book.exception.BookNotFoundException;
import com.example.demo.book.repository.BookRepository;
import com.example.demo.serial.utils.SerialUtils;
import com.example.demo.store.exception.StoreNotFoundException;
import com.example.demo.store.model.Store;
import com.example.demo.store.model.StoreBooks;
import com.example.demo.store.model.StoreDTO;
import com.example.demo.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class StoreService {

    private final StoreRepository repository;

    private final BookRepository bookRepository;

    public List<StoreDTO> findAll() {
        return repository.findAll().stream()
                .map(StoreDTO::build)
                .collect(Collectors.toList());
    }

    public StoreDTO findById(Long id) throws StoreNotFoundException {
        Store entity = repository.findById(id)
                .orElseThrow(StoreNotFoundException::new);
        return StoreDTO.build(entity);
    }

    public StoreDTO save(Long id, StoreDTO dto) throws StoreNotFoundException {
        if (id == null) id = SerialUtils.get("store");
        dto.setId(id);

        Store entity = new Store();
        BeanUtils.copyProperties(dto, entity, "books");

        if (!CollectionUtils.isEmpty(dto.getBooks())) {
            entity.setStoreBooks(dto.getBooks().stream()
                    .map(x -> {
                        try {
                            return convertToStoreBooks(entity, x);
                        } catch (BookNotFoundException e) {
                            return null;
                        }
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList()));
        }

        repository.deleteUnrelatedBooks(id, dto.getBooks());
        repository.save(entity);

        return findById(id);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    protected StoreBooks convertToStoreBooks(Store store, BookDTO dto) throws BookNotFoundException {
        Book book = bookRepository.findById(dto.getId())
                .orElseThrow(BookNotFoundException::new);
        return new StoreBooks(store, book, dto.getStock());
    }
}
