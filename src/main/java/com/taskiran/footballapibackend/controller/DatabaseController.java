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

// ** ------------------------------------------------------------------------------------------------------- **
//  
    @GetMapping("/getTeams1")
    public String GetTeamsPartOne(){

        List<String> leagues = new ArrayList<String>(List.of("UEFA Champions League", "Premier League"
                                                        , "La Liga", "Süper Lig"));
        for (String league : leagues){
            restTemplate.getForObject("http://localhost:8081/saveTeams?leagueName="+ league +"&season=2024", String.class);
        }
        return "Teams Part One : Requests sent successfully!";
    }

// ** ------------------------------------------------------------------------------------------------------- **
// UEFA'da 36 takım var. Request hakkı dakikada 30. O yüzden ayrı ayrı çekilmeli.
    @GetMapping("/getPlayers1")
    public String GetPlayersPartOne(){
        List<String> leagues = new ArrayList<String>(List.of("UEFA Champions League"));
        for (String league : leagues){
            restTemplate.getForObject("http://localhost:8081/savePlayers?leagueName="+ league, String.class);
        }
        return "Players Part One : Requests sent successfully!";
    }

// ** ------------------------------------------------------------------------------------------------------- **
// 
    @GetMapping("/getPlayers2")
    public String GetPlayersPartTwo(){
        List<String> leagues = new ArrayList<String>(List.of("Premier League"));
        for (String league : leagues){
            restTemplate.getForObject("http://localhost:8081/savePlayers?leagueName="+ league, String.class);
        }
        return "Players Part One : Requests sent successfully!";
    }

// ** ------------------------------------------------------------------------------------------------------- **
// 
    @GetMapping("/getPlayers3")
    public String GetPlayersPartThree(){
        List<String> leagues = new ArrayList<String>(List.of("La Liga", "Süper Lig"));
        for (String league : leagues){
            restTemplate.getForObject("http://localhost:8081/savePlayers?leagueName="+ league, String.class);
        }
        return "Players Part Two : Requests sent successfully!";
    }
    
// ** ------------------------------------------------------------------------------------------------------- **
}
