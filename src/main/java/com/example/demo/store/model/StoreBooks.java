package com.example.demo.store.model;

import com.example.demo.book.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "store_books")
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class StoreBooks implements Serializable {

    @EmbeddedId
    private StoreBooksId id;

    @ManyToOne
    @MapsId("storeId")
    @JoinColumn(name = "store_id")
    private Store store;

    @ManyToOne
    @MapsId("bookId")
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "stock")
    private Integer stock = 0;

    public StoreBooks(Store store, Book book, Integer stock) {
        this.id = new StoreBooksId(store.getId(), book.getId());
        this.store = store;
        this.book = book;
        this.stock = stock;
    }

    @Embeddable
    @Data
    @EqualsAndHashCode(callSuper = false, of = {"storeId", "bookId"})
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StoreBooksId implements Serializable {

        @Column(name = "store_id")
        private Long storeId;

        @Column(name = "book_id")
        private Long bookId;
    }
}
