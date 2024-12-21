package com.taskiran.footballapibackend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.taskiran.footballapibackend.entity.League;

public interface LeagueRepository extends JpaRepository<League, Long> {
    boolean existsByName(String name);  // Lig adını kontrol eden sorgu
    
    @Query("SELECT l.id FROM League l WHERE l.name = :name")
    Optional<Long> findIdByName(String name);  // Lig ismiyle lig bulur

    @Query("SELECT l.id FROM League l")
    List<Long> findAllLeagueIds();
    
    @Query("SELECT l.name FROM League l")
    List<String> findAllLeagueNames();
}
