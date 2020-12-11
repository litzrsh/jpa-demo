package com.example.demo.serial.repository;

import com.example.demo.serial.model.Serial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SerialRepository extends JpaRepository<Serial, String> {
}
