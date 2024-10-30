package com.taskiran.footballapibackend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.taskiran.footballapibackend.entity.Team;

public interface TeamRepository extends JpaRepository <Team, Long> {
    boolean existsByName(String name);
          
    @Query("SELECT l.id FROM Team l WHERE l.name = :name")
    Optional<Long> findTeamIdByName(String name);

    @Query("SELECT l.id FROM Team l")
    List<Long> findAllTeamIds();
}
