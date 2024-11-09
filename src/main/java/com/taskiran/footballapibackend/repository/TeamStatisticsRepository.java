package com.taskiran.footballapibackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskiran.footballapibackend.entity.teamstat.TeamStatistic;

public interface TeamStatisticsRepository extends JpaRepository <TeamStatistic, Long>{
    
    boolean existsByTeamIdAndLeagueId(Long teamId, Long leagueId);

    TeamStatistic findByTeamIdAndLeagueId(Long teamId, Long leagueId);
}
