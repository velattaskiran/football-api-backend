package com.taskiran.footballapibackend.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FootballApiService {

    private final String baseUrl = "https://api-football-v1.p.rapidapi.com/v3/leagues";
    private final String apiKey = "dc5141567cmsh404ce1a905247b5p1e1617jsnb7d7dcab8869"; 

    public String getLeaguesFromApi(String country) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", apiKey);
        headers.set("X-RapidAPI-Host", "api-football-v1.p.rapidapi.com");

        String url = baseUrl + "?country=" + country; 
        
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        
        return response.getBody();
    }
}
