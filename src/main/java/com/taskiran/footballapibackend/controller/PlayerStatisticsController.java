package com.taskiran.footballapibackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.taskiran.footballapibackend.repository.TeamLeaguesRepository;
import com.taskiran.footballapibackend.service.LeagueService;
import com.taskiran.footballapibackend.service.PlayerStatisticsService;
import com.taskiran.footballapibackend.service.TeamService;

@RestController
public class PlayerStatisticsController {

    @Autowired
    PlayerStatisticsService playerStatisticsService;

    @Autowired
    TeamService teamService;

    @Autowired
    LeagueService leagueService;

    @Autowired
    TeamLeaguesRepository teamLeaguesRepository;

// ** ------------------------------------------------------------------------------------------------------- **
// Save Players With Team Name & League Name
    @GetMapping("/savePlayerStatistics")
    public String savePlayerStatistics(@RequestParam String teamName, @RequestParam String leagueName){
        Long teamId = teamService.getTeamIdByName(teamName);
        Long leagueId = leagueService.getLeagueIdByName(leagueName);
        playerStatisticsService.savePlayerStatisticsToDatabase(teamId, leagueId);
        return ("For "+ leagueName + ", " + teamName + " players saved successfully!");
    }

// ** ------------------------------------------------------------------------------------------------------- **
// Save Players With Team ID & League ID
        @GetMapping("/savePlayerStatisticsByLeagueId")
        public String savePlayerStatisticsByLeagueId(@RequestParam Long leagueId) throws InterruptedException{
            List<Long> teamIds = teamLeaguesRepository.findAllTeamIdsByLeagueId(leagueId);
            for (Long teamId : teamIds){
                playerStatisticsService.savePlayerStatisticsToDatabase(teamId, leagueId);
            }
            return ("For "+ leagueId.toString() + " players saved successfully!");
        }
}
