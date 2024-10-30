package com.taskiran.footballapibackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.taskiran.footballapibackend.entity.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    boolean existsByName(String name);

    @Query("SELECT l.id FROM Player l WHERE l.teamId= :teamId")
    List<Long> findAllPlayersByTeamId(Long teamId);    
}
