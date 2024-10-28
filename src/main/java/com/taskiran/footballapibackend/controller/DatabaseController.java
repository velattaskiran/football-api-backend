package com.taskiran.footballapibackend.controller;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class DatabaseController {
    
    @Autowired
    private RestTemplate restTemplate;
    
    @GetMapping("/getTeams1")
    public String GetTeamsPartOne(){

        List<String> leagues = new ArrayList<String>(List.of("UEFA Champions League", "Premier League"
                                                        , "La Liga", "Süper Lig"));
        for (String league : leagues){
            String response = restTemplate.getForObject("http://localhost:8081/saveTeams?leagueName="+ league +"&season=2024", String.class);
            return "Response from " + league + response + " :";
        }
        return "Teams Part One : Requests sent successfully!";
    }
}
