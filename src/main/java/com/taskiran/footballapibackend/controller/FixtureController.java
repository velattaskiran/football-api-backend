package com.taskiran.footballapibackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.taskiran.footballapibackend.dto.AddFixturesRequest;
import com.taskiran.footballapibackend.service.FixtureService;

@RestController
public class FixtureController {

    @Autowired
    private FixtureService fixtureService;

// ** ------------------------------------------------------------------------------------------------------- **
// Save Players With League Name
    @PostMapping("/saveFixturesByLeagueName")
    public String saveFixturesByLeagueName(@RequestBody AddFixturesRequest request){
        return fixtureService.addFixturesByLeagueName(request);
    }
}
