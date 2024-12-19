package com.taskiran.footballapibackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskiran.footballapibackend.service.LeagueService;

@RestController
public class LeagueController {

    @Autowired
    private LeagueService leagueService;

    @PostMapping("/saveLeagues")
    public String saveLeagues() {
        return leagueService.addLeague();
    }
}
