package com.taskiran.footballapibackend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.taskiran.footballapibackend.entity.Player;
import com.taskiran.footballapibackend.repository.PlayerRepository;
import com.taskiran.footballapibackend.request.AddPlayerRequest;

@Service
public class PlayerService {
    @Autowired
    private FootballApiService footballApiService;

    @Autowired
    private TeamLeaguesService teamLeaguesService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public void savePlayersToDatabase(Long teamId) {
        try {
            // Seçilen takımlardan oyuncuları çek
            String json = footballApiService.getPlayersByTeamId(teamId);
    
            // JSON'u parse et
            JsonNode rootNode = objectMapper.readTree(json);
            JsonNode playersArray = rootNode.path("response").get(0).path("players");

            for (JsonNode playerNode : playersArray) {
                
                Player player = new Player();

                player.setId(playerNode.path("id").asLong());
                player.setName(playerNode.path("name").asText());
                player.setAge(playerNode.path("age").asLong());
                player.setNumber(playerNode.path("number").asLong());
                player.setPosition(playerNode.path("position").asText());
                player.setPhoto(playerNode.path("photo").asText());
                player.setTeamId(rootNode.path("response").get(0).path("team").path("id").asLong());
                player.setTeamName(rootNode.path("response").get(0).path("team").path("name").asText());
    
                // Veritabanına kaydet
                if (!playerRepository.existsByName(player.getName())) {
                    playerRepository.save(player);
                }                
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public List<Long> getAllPlayerIdsByTeamId(Long TeamId) {
        return playerRepository.findAllPlayersByTeamId(TeamId);
    }

    public String addPlayersByLeagueName(AddPlayerRequest request){
        List<Long> teamIds = teamLeaguesService.getTeamIdsByLeagueName(request.getLeagueName());
        for (Long teamId : teamIds){
            savePlayersToDatabase(teamId);
        }
        return "Players saved successfully!";
    }

    public String addPlayersByTeamName(AddPlayerRequest request){
        try{
            Long teamId = teamService.getTeamIdByName(request.getTeamName());
            savePlayersToDatabase(teamId);
            return "Players saved successfully!";
        }  catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return "Error: " + e.getMessage();
        }
    }
}
