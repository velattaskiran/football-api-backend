package com.taskiran.footballapibackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.taskiran.footballapibackend.service.TeamStatisticsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class TeamStatisticsController {

    @Autowired
    private TeamStatisticsService teamStatisticsService;
    
    @GetMapping("/saveTeamStatistics")    
    public String saveTeamStatisticsByTeamId(@RequestParam Long teamId, @RequestParam Long leagueId){
        try{
            teamStatisticsService.saveTeamStatisticsToDatabase(teamId, leagueId);
            return "Team Statistic saved successfully!";
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return "Error: " + e.getMessage();
        }
    
    }
}
