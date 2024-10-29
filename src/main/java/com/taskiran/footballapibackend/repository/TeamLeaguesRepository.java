package com.taskiran.footballapibackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.taskiran.footballapibackend.entity.TeamLeagues;
import com.taskiran.footballapibackend.entity.TeamLeaguesId;

public interface TeamLeaguesRepository extends JpaRepository <TeamLeagues, TeamLeaguesId>{
    
    @Query("SELECT tl.team.teamId FROM TeamLeagues tl WHERE tl.league.leagueId = :leagueId")
    List<Long> findAllTeamIdsByLeagueId(@Param("leagueId") Long leagueId);
}
