package com.taskiran.footballapibackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.taskiran.footballapibackend.request.AddPlayerRequest;
import com.taskiran.footballapibackend.service.PlayerService;

@RestController
public class PlayerController {
    
    @Autowired
    private PlayerService playerService;

// ** ------------------------------------------------------------------------------------------------------- **
// Save Players With League Name
    @PostMapping("/savePlayers")
    public String savePlayersByLeagueName(@RequestBody AddPlayerRequest request){
        return playerService.addPlayersByLeagueName(request);
    }

// ** ------------------------------------------------------------------------------------------------------- **
// Save Players With Team Name
    @PostMapping("/savePlayersByTeamName")
    public String savePlayersByTeamName(@RequestBody AddPlayerRequest request){
        return playerService.addPlayersByTeamName(request);
    }
}
