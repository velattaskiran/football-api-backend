package com.taskiran.footballapibackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.taskiran.footballapibackend.dto.AddPlayerStatisticRequest;
import com.taskiran.footballapibackend.service.PlayerStatisticsService;

@RestController
public class PlayerStatisticsController {

    @Autowired
    PlayerStatisticsService playerStatisticsService;

// ** ------------------------------------------------------------------------------------------------------- **
// Save Players With Team Name & League Name
    @PostMapping("/savePlayerStatistics")
    public String savePlayerStatistics(@RequestBody AddPlayerStatisticRequest request){
        return playerStatisticsService.addPlayerStatistics(request);
    }

// ** ------------------------------------------------------------------------------------------------------- **
// Save Players With League Name
    @PostMapping("/savePlayerStatisticsByLeagueName")
    public String savePlayerStatisticsByLeagueName(@RequestBody AddPlayerStatisticRequest request){
        return playerStatisticsService.addPlayerStatisticsByLeagueName(request);
    }
}
