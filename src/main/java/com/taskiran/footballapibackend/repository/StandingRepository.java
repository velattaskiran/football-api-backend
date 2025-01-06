package com.taskiran.footballapibackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskiran.footballapibackend.entity.Standing;

public interface StandingRepository extends JpaRepository<Standing, Long>{

    boolean existsByTeamIdAndLeagueId(Long teamId, Long leagueId);
    
    Standing findByTeamIdAndLeagueId(Long teamId, Long leagueId);
}
