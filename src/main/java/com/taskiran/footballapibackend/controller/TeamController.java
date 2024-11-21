package com.taskiran.footballapibackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.taskiran.footballapibackend.request.AddTeamRequest;
import com.taskiran.footballapibackend.service.TeamService;

@RestController
public class TeamController {

    @Autowired
    private TeamService teamService;

    @PostMapping("/saveTeams")
    public String saveTeamsByLeagueName(@RequestBody AddTeamRequest request) {
        return teamService.addTeam(request);
    }
}
