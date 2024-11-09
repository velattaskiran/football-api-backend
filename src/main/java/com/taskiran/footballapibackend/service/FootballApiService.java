package com.taskiran.footballapibackend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.taskiran.footballapibackend.controller.DatabaseController;


@Service
public class FootballApiService {

    @Autowired
    private DatabaseController databaseController;
    
// ** ------------------------------------------------------------------------------------------------------- **
// Base URL & API KEY

    String baseUrl = "https://api-football-v1.p.rapidapi.com/v3";
    public static List<String> apiKeyList =  new ArrayList<>();
    static{
        apiKeyList.add("fb4ef21728msh72436ad99c90381p175dd3jsn75b1ce6c70cc");           // welattaskiran@gmail.com        
        apiKeyList.add("dc5141567cmsh404ce1a905247b5p1e1617jsnb7d7dcab8869");           // velattaskiran@gmail.com
    }

// ** ------------------------------------------------------------------------------------------------------- **
// Get Leagues

    public String getLeaguesFromApi(String country) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", apiKeyList.get(0));
        headers.set("X-RapidAPI-Host", "api-football-v1.p.rapidapi.com");

        String url = baseUrl + "/leagues?country=" + country;
        
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        
        return response.getBody();
    }

// ** ------------------------------------------------------------------------------------------------------- **
// Get Teams

    public String  getTeamsByLeagueId(Long leagueId, Long season) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", apiKeyList.get(0));
        headers.set("X-RapidAPI-Host", "api-football-v1.p.rapidapi.com");

        String url = baseUrl + "/standings?league=" + leagueId.toString() + "&season=" + season.toString();

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        return response.getBody();
    }

// ** ------------------------------------------------------------------------------------------------------- **
// Get Players

    public String getPlayersByTeamId(Long teamId) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", apiKeyList.get(0));
        headers.set("X-RapidAPI-Host", "api-football-v1.p.rapidapi.com");
        
        String url = baseUrl + "/players/squads?team=" + teamId.toString();
        
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        return response.getBody();
    }

// ** ------------------------------------------------------------------------------------------------------- **
// Get Player Statistics

    public String getPlayerStatisticsByTeamIdAndLeagueId(Long teamId, Long leagueId, Integer pageNo){

        Integer currentRequestCount = databaseController.getRequestCount();
        if (currentRequestCount > 96) {
            Integer apiCount = databaseController.getApiCount();
            databaseController.setApiCount(apiCount + 1);
        }else {
            databaseController.setRequestCount(currentRequestCount + 1);
        }

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", apiKeyList.get(databaseController.getApiCount()));
        headers.set("X-RapidAPI-Host", "api-football-v1.p.rapidapi.com");
        
        String url = baseUrl + "/players?team=" + teamId.toString() + "&league="+ leagueId.toString() + "&season=2024&page=" + pageNo.toString();
        
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        return response.getBody();
    }

// ** ------------------------------------------------------------------------------------------------------- **
// Get Team Statistics
    public String getTeamStatisticsByTeamIdAndLeagueId(Long teamId, Long leagueId) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", apiKeyList.get(databaseController.getApiCount()));
        headers.set("X-RapidAPI-Host", "api-football-v1.p.rapidapi.com");
        
        String url = baseUrl + "/teams/statistics?league=" + leagueId.toString()  + "&season=2024&team="  + teamId.toString();
        
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        return response.getBody();
    }
}
