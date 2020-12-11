package com.example.demo.store.repository;

import com.example.demo.store.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long>, QStoreRepository {

    List<Store> findAll();
}
