package com.taskiran.footballapibackend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.taskiran.footballapibackend.entity.League;
import com.taskiran.footballapibackend.repository.LeagueRepository;

@Service
public class LeagueService {
     @Autowired
    private FootballApiService footballApiService;

    @Autowired
    private LeagueRepository leagueRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public void saveLeaguesToDatabase(String country, List<String> selectedLeagues) {
        try {
            // Seçilen ülke ile API'den ligleri çek
            String json = footballApiService.getLeaguesFromApi(country);
    
            // JSON'u ayrıştır
            JsonNode rootNode = objectMapper.readTree(json);
            JsonNode leaguesArray = rootNode.path("response");
    
            for (JsonNode leagueNode : leaguesArray) {
                String leagueName = leagueNode.path("league").path("name").asText();
    
                // Sadece seçili ligler listesinde olanları işle
                if (selectedLeagues.contains(leagueName)) {
                    League league = new League();
                    league.setId(leagueNode.path("league").path("id").asInt());
                    league.setName(leagueName);
                    league.setCountry(leagueNode.path("country").path("name").asText());
                    league.setIconPath(leagueNode.path("league").path("logo").asText());
    
                    // Veritabanına kaydet
                    if (!leagueRepository.existsByName(league.getName())) {
                        leagueRepository.save(league);
                    }
                }
            }
    
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
