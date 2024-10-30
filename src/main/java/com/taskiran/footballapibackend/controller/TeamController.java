package com.taskiran.footballapibackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.taskiran.footballapibackend.service.LeagueService;
import com.taskiran.footballapibackend.service.TeamService;

@RestController
public class TeamController {

    @Autowired
    private TeamService teamService;

    @Autowired
    private LeagueService leagueService;

    @GetMapping("/saveTeams")
    public String saveTeamsByLeagueName(@RequestParam String leagueName, @RequestParam Long season) {
        try {
            Long leagueId = leagueService.getLeagueIdByName(leagueName);
                        
            teamService.saveTeamsToDatabase(leagueId, season);
            return "Teams saved successfully!";
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return "Error: " + e.getMessage();
        }
    }
}
