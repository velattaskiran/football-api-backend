package com.taskiran.footballapibackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.taskiran.footballapibackend.dto.AddTeamRequest;
import com.taskiran.footballapibackend.service.StandingService;

@RestController
public class StandingController {

    @Autowired
    private StandingService standingService;

// ** ------------------------------------------------------------------------------------------------------- **
// Save Players With League Name
    @PostMapping("/saveStandingsByLeagueName")
    public String saveStandingsByLeagueName(@RequestBody AddTeamRequest request){
        return standingService.addStandingsByLeagueName(request);
    }
}
