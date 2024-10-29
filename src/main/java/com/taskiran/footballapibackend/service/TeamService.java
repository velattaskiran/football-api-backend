package com.taskiran.footballapibackend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.taskiran.footballapibackend.entity.League;
import com.taskiran.footballapibackend.entity.Team;
import com.taskiran.footballapibackend.entity.TeamLeagues;
import com.taskiran.footballapibackend.repository.TeamLeaguesRepository;
import com.taskiran.footballapibackend.repository.TeamRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TeamService {
    @Autowired
    private FootballApiService footballApiService;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private TeamLeaguesRepository teamLeaguesRepository;

    @Autowired
    ObjectMapper objectMapper = new ObjectMapper();

    public void saveTeamsToDatabase(Long leagueId, Long season) {
        try {
            // Seçilen takımlardan oyuncuları çek
            String json = footballApiService.getTeamsByLeagueId(leagueId, season);            

            // JSON'u parse et
            JsonNode rootNode = objectMapper.readTree(json);
            JsonNode teamsArray = rootNode.path("response");

            if (teamsArray.isArray() && teamsArray.size() > 0) {
                // league içinden standings'a git
                JsonNode standingsNode = teamsArray.get(0).path("league").path("standings");
    
                // Her bir takım için standings'ten al
                for (JsonNode standing : standingsNode) {
                    for (JsonNode teamNode : standing) {
                        Team team = new Team();
                        team.setTeamId(teamNode.path("team").path("id").asLong());
                        team.setName(teamNode.path("team").path("name").asText());
                        team.setLogo(teamNode.path("team").path("logo").asText());
                        
                        teamRepository.save(team);

                        // Team_Leagues ilişkisini oluştur ve kaydet
                        TeamLeagues teamLeagues = new TeamLeagues();
                        teamLeagues.setTeam(team);
                        teamLeagues.setLeague(new League(leagueId));  // İlgili League ID'yi ayarla

                        teamLeaguesRepository.save(teamLeagues);
                    }
                }
            } else {
                throw new Exception("No teams found for the given league ID and season.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Long getTeamIdByName(String teamName) {
        return teamRepository.findTeamIdByName(teamName)
            .orElseThrow(() -> new EntityNotFoundException("League not found"));
    }


    public List<Long> getAllTeamIds() {
        return teamRepository.findAllTeamIds();
    }
    
}
