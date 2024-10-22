package com.taskiran.footballapibackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.taskiran.footballapibackend.entity.Player;
import com.taskiran.footballapibackend.repository.PlayerRepository;

@Service
public class PlayerService {
    @Autowired
    private FootballApiService footballApiService;

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
                player.setAge(playerNode.path("age").asInt());
                player.setNumber(playerNode.path("number").asInt());
                player.setPosition(playerNode.path("position").asText());
                player.setPhoto(playerNode.path("photo").asText());
                player.setTeamId(rootNode.path("response").get(0).path("team").path("id").asInt());
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
}
