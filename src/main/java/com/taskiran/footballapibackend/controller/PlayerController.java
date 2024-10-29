package com.taskiran.footballapibackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.taskiran.footballapibackend.service.PlayerService;
import com.taskiran.footballapibackend.service.TeamLeaguesService;
import com.taskiran.footballapibackend.service.TeamService;

@RestController
public class PlayerController {
    
    @Autowired
    private PlayerService playerService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private TeamLeaguesService teamLeaguesService;

    @GetMapping("/savePlayers")
    public String savePlayers(@RequestParam String leagueName){
        List<Long> teamIds = teamLeaguesService.getTeamIdsByLeagueName(leagueName);
        for (Long teamId : teamIds){
            playerService.savePlayersToDatabase(teamId);
        }
        return "Players saved successfully!";
    }

    @GetMapping("/savePlayersWithTeamName")
    public String savePlayersWithTeamName(@RequestParam String teamName){
        Long teamId = teamService.getTeamIdByName(teamName);
        playerService.savePlayersToDatabase(teamId);
        return "Players saved successfully!";
    }
    
    @GetMapping("/savePlayersWithTeamId")
    public String savePlayersWithTeamId(@RequestParam Long teamId){
        playerService.savePlayersToDatabase(teamId);
        return "Players saved successfully!";
    }
}
