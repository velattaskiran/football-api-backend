package com.taskiran.footballapibackend.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.taskiran.footballapibackend.entity.League;
import com.taskiran.footballapibackend.repository.LeagueRepository;

import jakarta.persistence.EntityNotFoundException;

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
            // TODO: Add new object for json in dto, mapping example -> https://www.baeldung.com/jackson-mapping-dynamic-object
            // TODO: add validation method in helper class


            for (JsonNode leagueNode : leaguesArray) {
                
                String leagueName = leagueNode.path("league").path("name").asText();
    
                // Sadece seçili ligler listesinde olanları işle
                if (selectedLeagues.contains(leagueName)) {
                    League league = new League();
                    league.setLeagueId(leagueNode.path("league").path("id").asLong());
                    league.setName(leagueName);
                    league.setCountry(leagueNode.path("country").path("name").asText());
                    league.setLogoUrl(leagueNode.path("league").path("logo").asText());
    
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

    public Long getLeagueIdByName(String leagueName) {
        return leagueRepository.findIdByName(leagueName)
            .orElseThrow(() -> new EntityNotFoundException("League not found"));
    }

// ** ------------------------------------------------------------------------------------------------------- **
// Save Leagues
    public String addLeague(){
        try{
            saveLeaguesToDatabase("Turkey", Arrays.asList("Süper Lig"));
            saveLeaguesToDatabase("World", Arrays.asList("UEFA Champions League"));
            saveLeaguesToDatabase("England", Arrays.asList("Premier League"));
            saveLeaguesToDatabase("Spain", Arrays.asList("La Liga"));
            return "Leagues saved successfully!";
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return "Error: " + e.getMessage();
        }
    }    
}
