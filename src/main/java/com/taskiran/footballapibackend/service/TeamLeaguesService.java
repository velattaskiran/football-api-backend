package com.taskiran.footballapibackend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskiran.footballapibackend.repository.TeamLeaguesRepository;

@Service
public class TeamLeaguesService {

    @Autowired
    private TeamLeaguesRepository teamLeaguesRepository;
    
    @Autowired
    private LeagueService leagueService;

    public List<Long>  getTeamIdsByLeagueName(String leagueName) {
        Long leagueId = leagueService.getLeagueIdByName(leagueName);
        if (leagueId == null) {
            throw new IllegalArgumentException("League not found: " + leagueName);
        }
        return teamLeaguesRepository.findAllTeamIdsByLeagueId(leagueId);
    }
}
