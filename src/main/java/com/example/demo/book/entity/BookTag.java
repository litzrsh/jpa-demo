package com.example.demo.book.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "book_tags")
@Data
@EqualsAndHashCode(callSuper = false, of = "id")
@NoArgsConstructor
@AllArgsConstructor
public class BookTag {

    @EmbeddedId
    private BookTagId id;

    @ManyToOne
    @MapsId("bookId")
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "tag_val", insertable = false, updatable = false)
    private String tag;

    public BookTag(Book book, String tag) {
        this.id = new BookTagId(book.getId(), tag);
        this.book = book;
        this.tag = tag;
    }

    @Embeddable
    @Data
    @EqualsAndHashCode(callSuper = false, of = {"bookId", "tag"})
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BookTagId implements Serializable {

        @Column(name = "book_id")
        private Long bookId;

        @Column(name = "tag_val")
        private String tag;
    }
}
