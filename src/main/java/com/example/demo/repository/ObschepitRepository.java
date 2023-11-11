package com.example.demo.repository;

import com.example.demo.domain.entity.Obschepit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ObschepitRepository extends JpaRepository<Obschepit, UUID> {

    @Query(value = "SELECT p FROM Obschepit p")
    List<Obschepit> getAll();
    Optional<Obschepit> findByName(String name);

    Boolean existsByName(String name);
}
