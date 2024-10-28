package com.taskiran.footballapibackend.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.taskiran.footballapibackend.entity.PlayerStatistic;
import com.taskiran.footballapibackend.repository.PlayerStatisticsRepository;

public class PlayerStatisticsService {
    @Autowired
    private FootballApiService footballApiService;

    @Autowired
    private PlayerStatisticsRepository playerStatisticsRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public void savePlayerStatisticsToDatabase(Long playerId, Long leagueId) {
        try {
            String json = footballApiService.getPlayerStatisticsByPlayerId(playerId);

            JsonNode rootNode = objectMapper.readTree(json);
            JsonNode playerStatisticsArray = rootNode.path("response").get(0).path("statistics");
            JsonNode playerNode = rootNode.path("response").get(0).path("player");

            for (JsonNode playerStatisticNode : playerStatisticsArray) {

                PlayerStatistic playerStatistic = new PlayerStatistic();

                playerStatistic.setPlayerId(playerNode.path("id").asLong());
                playerStatistic.setTeamId(playerStatisticNode.path("team").path("id").asLong());
                playerStatistic.setLeagueId(playerStatisticNode.path("league").path("id").asLong());
                playerStatistic.setSeason(playerStatisticNode.path("league").path("season").asLong());
                playerStatistic.setAppearences(playerStatisticNode.path("games").path("appearences").asLong());
                playerStatistic.setLineups(playerStatisticNode.path("games").path("lineups").asLong());
                playerStatistic.setMinutes(playerStatisticNode.path("games").path("minutes").asLong());
                playerStatistic.setRating(playerStatisticNode.path("games").path("rating").asLong()); // şurası kontrol edilecek "asLong() ??"
                playerStatistic.setSubstitutesIn(playerStatisticNode.path("substitutes").path("in").asLong());
                playerStatistic.setSubstitutesOut(playerStatisticNode.path("substitutes").path("out").asLong());
                playerStatistic.setSubstitutesBench(playerStatisticNode.path("substitutes").path("bench").asLong());
                playerStatistic.setShotsTotal(playerStatisticNode.path("shots").path("total").asLong());
                playerStatistic.setShotsOn(playerStatisticNode.path("shots").path("on").asLong());
                playerStatistic.setGoals(playerStatisticNode.path("goals").path("total").asLong());
                playerStatistic.setGoalConceded(playerStatisticNode.path("goals").path("conceded").asLong());
                playerStatistic.setAssists(playerStatisticNode.path("goals").path("assists").asLong());
                playerStatistic.setGoalSaves(playerStatisticNode.path("goals").path("saves").asLong());
                playerStatistic.setPasses(playerStatisticNode.path("passes").path("total").asLong());
                playerStatistic.setPassesKey(playerStatisticNode.path("passes").path("key").asLong());
                playerStatistic.setPassesAccurracy(playerStatisticNode.path("passes").path("accurracy").asLong());
                playerStatistic.setTackles(playerStatisticNode.path("tackles").path("total").asLong());
                playerStatistic.setTacklesBlocks(playerStatisticNode.path("tackles").path("blocks").asLong());
                playerStatistic.setTacklesInterceptions(playerStatisticNode.path("tackles").path("interceptions").asLong());
                playerStatistic.setDuels(playerStatisticNode.path("duels").path("total").asLong());
                playerStatistic.setDuelsWon(playerStatisticNode.path("duels").path("won").asLong());
                playerStatistic.setDribblesAttempts(playerStatisticNode.path("dribbles").path("attempts").asLong());
                playerStatistic.setDribblesSuccess(playerStatisticNode.path("dribbles").path("success").asLong());
                playerStatistic.setFoulsDrawn(playerStatisticNode.path("fouls").path("drawn").asLong());
                playerStatistic.setFoulsCommitted(playerStatisticNode.path("fouls").path("committed").asLong());
                playerStatistic.setCardsYellow(playerStatisticNode.path("cards").path("yellow").asLong());
                playerStatistic.setCardsYellowRed(playerStatisticNode.path("cards").path("yellowred").asLong());
                playerStatistic.setCardsRed(playerStatisticNode.path("cards").path("red").asLong());
                playerStatistic.setPenaltyWon(playerStatisticNode.path("penalty").path("won").asLong());
                playerStatistic.setPenaltyCommitted(playerStatisticNode.path("penalty").path("commited").asLong());
                playerStatistic.setPenaltyScored(playerStatisticNode.path("penalty").path("scored").asLong());
                playerStatistic.setPenaltyMissed(playerStatisticNode.path("penalty").path("missed").asLong());
                playerStatistic.setPenaltySaved(playerStatisticNode.path("penalty").path("saved").asLong());

                
                // Veritabanına kaydet
                playerStatisticsRepository.save(playerStatistic);              
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
