package com.taskiran.footballapibackend.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskiran.footballapibackend.service.LeagueService;

@RestController
public class LeagueController {

    @Autowired
    private LeagueService leagueService;

    @GetMapping("/saveLeagues")
    public String saveLeagues() {
        leagueService.saveLeaguesToDatabase("Turkey", Arrays.asList("Süper Lig"));
        leagueService.saveLeaguesToDatabase("World", Arrays.asList("UEFA Champions League"));
        leagueService.saveLeaguesToDatabase("England", Arrays.asList("Premier League"));
        leagueService.saveLeaguesToDatabase("Spain", Arrays.asList("La Liga"));
        return "Leagues saved successfully!";
    }

}
