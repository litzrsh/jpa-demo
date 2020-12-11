package com.example.demo.store.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "store")
@Data
@EqualsAndHashCode(callSuper = false, of = "id")
@NoArgsConstructor
@AllArgsConstructor
public class Store implements Serializable {

    @Id
    @Column(name = "store_id")
    private Long id;

    @Column(name = "store_nm")
    private String name;

    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "store",
            orphanRemoval = true)
    private List<StoreBooks> storeBooks = new ArrayList<>();
}
