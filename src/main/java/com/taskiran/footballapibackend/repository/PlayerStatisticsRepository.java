package com.taskiran.footballapibackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.taskiran.footballapibackend.entity.PlayerStatistic;

public interface PlayerStatisticsRepository extends JpaRepository <PlayerStatistic, Long>{

    boolean existsByPlayerIdAndLeagueId(Long playerId, Long leagueId);
     
    PlayerStatistic findByPlayerIdAndLeagueId(Long playerId, Long leagueId);

}