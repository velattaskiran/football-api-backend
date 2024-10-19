package com.taskiran.footballapibackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskiran.footballapibackend.entity.Team;

public interface TeamRepository extends JpaRepository <Team, Long> {
    boolean existsByName(String name);  // Lig adını kontrol eden sorgu
}
