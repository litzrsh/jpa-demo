package com.example.demo.book.service;

import com.example.demo.book.entity.Book;
import com.example.demo.book.entity.BookDTO;
import com.example.demo.book.entity.BookTag;
import com.example.demo.book.exception.BookNotFoundException;
import com.example.demo.book.repository.BookRepository;
import com.example.demo.serial.utils.SerialUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class BookService {

    private final BookRepository repository;

    public List<BookDTO> findAll() {
        return repository.findAll().stream()
                .map(BookDTO::build)
                .collect(Collectors.toList());
    }

    public BookDTO findById(Long id) throws BookNotFoundException {
        Book entity = repository.findById(id)
                .orElseThrow(BookNotFoundException::new);
        return BookDTO.build(entity);
    }

    public BookDTO save(Long id, BookDTO dto) throws BookNotFoundException {
        if (id == null) id = SerialUtils.get("book");
        dto.setId(id);

        Book entity = new Book();
        BeanUtils.copyProperties(dto, entity, "tags");

        if (!CollectionUtils.isEmpty(dto.getTags())) {
            entity.getTags().addAll(
                    dto.getTags().stream()
                            .map(tag -> new BookTag(entity, tag))
                            .collect(Collectors.toList()));
        }

        repository.save(entity);

        return findById(id);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
