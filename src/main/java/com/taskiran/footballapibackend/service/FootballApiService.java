package com.taskiran.footballapibackend.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class FootballApiService {
    
//  ** ------------------------------------------------------------------------------------------------------ **
// Base URL & API KEY

    private final String baseUrl = "https://api-football-v1.p.rapidapi.com/v3";
    private final String apiKey = "fb4ef21728msh72436ad99c90381p175dd3jsn75b1ce6c70cc";

//  ** ------------------------------------------------------------------------------------------------------ **
// Get Leagues

    public String getLeaguesFromApi(String country) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", apiKey);
        headers.set("X-RapidAPI-Host", "api-football-v1.p.rapidapi.com");

        String url = baseUrl + "/leagues?country=" + country;
        
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        
        return response.getBody();
    }

//  ** ------------------------------------------------------------------------------------------------------ **
// Get Teams

    public String  getTeamsByLeagueId(Long leagueId, Long season) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", apiKey);
        headers.set("X-RapidAPI-Host", "api-football-v1.p.rapidapi.com");

        String url = baseUrl + "/standings?league=" + leagueId.toString() + "&season=" + season.toString();

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        return response.getBody();
    }

//  ** ------------------------------------------------------------------------------------------------------ **
// Get Players

    public String getPlayersByTeamId(Long teamId) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", apiKey);
        headers.set("X-RapidAPI-Host", "api-football-v1.p.rapidapi.com");
        
        String url = baseUrl + "/players/squads?team=" + teamId.toString();
        
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        return response.getBody();
    }

//  ** ------------------------------------------------------------------------------------------------------ **
// Get Player Statistics

    public String getPlayerStatisticsByPlayerId(Long playerId){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", apiKey);
        headers.set("X-RapidAPI-Host", "api-football-v1.p.rapidapi.com");
        
        String url = baseUrl + "/players?id=" + playerId.toString() + "&seasons=2024";
        
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        return response.getBody();
    }


//  ** ------------------------------------------------------------------------------------------------------ **
}
