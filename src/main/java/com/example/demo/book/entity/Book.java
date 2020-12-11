package com.example.demo.book.entity;

import com.example.demo.store.model.StoreBooks;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "book")
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Book implements Serializable {

    @Id
    @Column(name = "book_id")
    private Long id;

    @Column(name = "book_nm")
    private String title;

    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "book",
            orphanRemoval = true)
    private List<BookTag> tags = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "book",
            orphanRemoval = true)
    private List<StoreBooks> storeBooks = new ArrayList<>();
}