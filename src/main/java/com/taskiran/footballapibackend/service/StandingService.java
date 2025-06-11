package com.taskiran.footballapibackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.taskiran.footballapibackend.dto.AddTeamRequest;
import com.taskiran.footballapibackend.entity.Standing;
import com.taskiran.footballapibackend.repository.StandingRepository;

@Service
public class StandingService {
    @Autowired
    private FootballApiService footballApiService;

    @Autowired
    private StandingRepository standingRepository;

    @Autowired
    private LeagueService leagueService;
    
    @Autowired
    ObjectMapper objectMapper = new ObjectMapper();

    public void saveStandingToDatabase(Long leagueId, Long season){
        try {
            // Seçilen takımlardan oyuncuları çek
            String json = footballApiService.getTeamsByLeagueId(leagueId, season);            

            // JSON'u parse et
            JsonNode rootNode = objectMapper.readTree(json);
            JsonNode teamsArray = rootNode.path("response");

            Standing team = null;

            if (teamsArray.isArray() && teamsArray.size() > 0) {
                JsonNode leagueNode = teamsArray.get(0).path("league");
                JsonNode standingsNode = teamsArray.get(0).path("league").path("standings");

                for (JsonNode standing : standingsNode) {
                    for (JsonNode teamNode : standing) {
                        
                        if (standingRepository.existsByTeamIdAndLeagueId(teamNode.path("team").path("id").asLong(), leagueNode.path("id").asLong())){
                            team = standingRepository.findByTeamIdAndLeagueId(teamNode.path("team").path("id").asLong(), leagueNode.path("id").asLong());
                        }else {
                            team = new Standing();
                        }

                        team.setLeagueId(leagueNode.path("id").asLong());
                        team.setLeagueName(leagueNode.path("name").asText());
                        team.setTeamId(teamNode.path("team").path("id").asLong());
                        team.setTeamName(teamNode.path("team").path("name").asText());
                        team.setTeamLogo(teamNode.path("team").path("logo").asText());
                        team.setRank(teamNode.path("rank").asInt());
                        team.setPoints(teamNode.path("points").asInt());
                        team.setGoalsDiff(teamNode.path("goalsDiff").asInt());
                        team.setForm(teamNode.path("form").asText());
                        team.setDescription(teamNode.path("description").asText());
                        team.setPlayed(teamNode.path("all").path("played").asInt());
                        team.setWin(teamNode.path("all").path("win").asInt());
                        team.setDraw(teamNode.path("all").path("draw").asInt());
                        team.setLose(teamNode.path("all").path("lose").asInt());
                        team.setGoalsFor(teamNode.path("all").path("goals").path("for").asInt());
                        team.setGoalsAgainst(teamNode.path("all").path("goals").path("against").asInt());
                        team.setHomePlayed(teamNode.path("home").path("played").asInt());
                        team.setHomeWin(teamNode.path("home").path("win").asInt());
                        team.setHomeDraw(teamNode.path("home").path("draw").asInt());
                        team.setHomeLose(teamNode.path("home").path("lose").asInt());
                        team.setHomeGoalsFor(teamNode.path("home").path("goals").path("for").asInt());
                        team.setHomeGoalsAgainst(teamNode.path("home").path("goals").path("against").asInt());
                        team.setAwayPlayed(teamNode.path("away").path("played").asInt());
                        team.setAwayWin(teamNode.path("away").path("win").asInt());
                        team.setAwayDraw(teamNode.path("away").path("draw").asInt());
                        team.setAwayLose(teamNode.path("away").path("lose").asInt());
                        team.setAwayGoalsFor(teamNode.path("away").path("goals").path("for").asInt());
                        team.setAwayGoalsAgainst(teamNode.path("away").path("goals").path("against").asInt());
                        team.setUpdate(teamNode.path("update").asText());

                        standingRepository.save(team);
                    }
                }
            } else {
                throw new Exception("No standings founf for the given league ID and season");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String addStandingsByLeagueName(AddTeamRequest request) {
        Long leagueId = leagueService.getLeagueIdByName(request.getLeagueName());
        saveStandingToDatabase(leagueId, request.getSeason());
        return "Standing saved successfully!";
    }

}
