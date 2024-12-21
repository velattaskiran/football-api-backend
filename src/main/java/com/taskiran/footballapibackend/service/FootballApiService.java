package com.taskiran.footballapibackend.service;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Service
public class FootballApiService {

    // TODO: Rest API yerine feign client /// https://www.javacodegeeks.com/2024/04/feign-vs-resttemplate-picking-your-spring-client.html
    
// ** ------------------------------------------------------------------------------------------------------- **
// Request Count & Max
    final Integer maxRequestNumber = 90;
    private Integer requestCount = 0;

// ** ------------------------------------------------------------------------------------------------------- **
// Base URL & API KEY

    String baseUrl = "https://api-football-v1.p.rapidapi.com/v3";

    private Integer apiIndex = 1;

    private static final List<String> apiKeyList =  List.of(
        "fb4ef21728msh72436ad99c90381p175dd3jsn75b1ce6c70cc",       // welattaskiran@gmail.com        
        "dc5141567cmsh404ce1a905247b5p1e1617jsnb7d7dcab8869",       // velattaskiran@gmail.com
        "f4665ec969mshadc960b76f17024p126073jsnca246195ab51",       // zekiitaskiran@gmail.com
        "3a2a3d14abmshc90d74a217fe62ap1c5870jsn3335ede767b4"        // taskiranwelatt@gmail.com
    );

// ** ------------------------------------------------------------------------------------------------------- **
// General API Request
    private String makeApiRequest(String endpoint, Integer apiCount){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", apiKeyList.get(apiCount));
        headers.set("X-RapidAPI-Host", "api-football-v1.p.rapidapi.com");

        String url = baseUrl + endpoint;
        
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        
        checkRequestCount();

        return response.getBody();
    }

// ** ------------------------------------------------------------------------------------------------------- **
// Get Leagues

    public String getLeaguesFromApi(String country) {
        String endpoint = "/leagues?country=" + country;
        return makeApiRequest(endpoint, apiIndex);
    }

// ** ------------------------------------------------------------------------------------------------------- **
// Get Teams

    public String  getTeamsByLeagueId(Long leagueId, Long season) throws Exception {
        String endpoint = "/standings?league=" + leagueId.toString() + "&season=" + season.toString();
        return makeApiRequest(endpoint, apiIndex);
    }

// ** ------------------------------------------------------------------------------------------------------- **
// Get Players

    public String getPlayersByTeamId(Long teamId) {
        String endpoint = "/players/squads?team=" + teamId.toString();
        return makeApiRequest(endpoint, apiIndex);
    }

// ** ------------------------------------------------------------------------------------------------------- **
// Get Player Statistics

    public String getPlayerStatisticsByTeamIdAndLeagueId(Long teamId, Long leagueId, Integer pageNo){
        String endpoint = "/players?team=" + teamId.toString() + "&league="+ leagueId.toString() + "&season=2024&page=" + pageNo.toString();
        return makeApiRequest(endpoint, apiIndex);
    }

// ** ------------------------------------------------------------------------------------------------------- **
// Get Team Statistics
    public String getTeamStatisticsByTeamIdAndLeagueId(Long teamId, Long leagueId) {
        String endpoint = "/teams/statistics?league=" + leagueId.toString()  + "&season=2024&team="  + teamId.toString();
        return makeApiRequest(endpoint, apiIndex);
    }

// ** ------------------------------------------------------------------------------------------------------- **
// Check Request Count
    private void checkRequestCount(){
        requestCount++;
        if (requestCount > maxRequestNumber) {
            requestCount = 0;
            apiIndex++;
        }
    }
}
