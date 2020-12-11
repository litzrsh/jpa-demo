package com.example.demo.store.repository;

import com.example.demo.book.entity.BookDTO;
import com.example.demo.store.model.QStoreBooks;
import com.example.demo.store.model.Store;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.stream.Collectors;

public class QStoreRepositoryImpl extends QuerydslRepositorySupport implements QStoreRepository {

    public QStoreRepositoryImpl() {
        super(Store.class);
    }

    @Override
    public void deleteUnrelatedBooks(Long id, List<BookDTO> books) {
        List<Long> array = books.stream()
                .map(BookDTO::getId)
                .collect(Collectors.toList());

        QStoreBooks storeBooks = QStoreBooks.storeBooks;
        delete(storeBooks)
                .where(storeBooks.id.storeId.eq(id)
                        .and(storeBooks.id.bookId.notIn(array)))
                .execute();
    }
}
