package com.taskiran.footballapibackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.taskiran.footballapibackend.dto.AddTeamStatisticsRequest;
import com.taskiran.footballapibackend.service.TeamStatisticsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class TeamStatisticsController {

    @Autowired
    private TeamStatisticsService teamStatisticsService;
    
    @PostMapping("/saveTeamStatistics")    
    public String saveTeamStatisticsByTeamName(@RequestBody AddTeamStatisticsRequest request){
        return teamStatisticsService.addTeamStatistics(request);    
    }
}
