package com.taskiran.footballapibackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskiran.footballapibackend.entity.League;

public interface LeagueRepository extends JpaRepository<League, Long> {
    boolean existsByName(String name);  // Lig adını kontrol eden sorgu    
}
