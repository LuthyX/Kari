package com.example.springsecurity.repositories;

import com.example.springsecurity.models.Package;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface PackageRepository extends JpaRepository<Package, Long> {

    Package findByTrackcode(String trackcode);

    List<Package> findByCustomerid(Long id);
}
