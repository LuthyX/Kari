package com.example.springsecurity.repositories;

import com.example.springsecurity.models.History;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoryRepository extends JpaRepository<History, Long> {

    List<History> findByCustomerid(Long id);
}
