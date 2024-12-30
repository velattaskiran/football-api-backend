package com.taskiran.footballapibackend.service;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.taskiran.footballapibackend.dto.AddTeamStatisticsRequest;
import com.taskiran.footballapibackend.entity.teamstat.BiggestStatistic;
import com.taskiran.footballapibackend.entity.teamstat.FixtureStatistic;
import com.taskiran.footballapibackend.entity.teamstat.GoalStatistic;
import com.taskiran.footballapibackend.entity.teamstat.TeamStatistic;
import com.taskiran.footballapibackend.repository.TeamStatisticsRepository;

@Service
public class TeamStatisticsService {

    @Autowired
    private TeamStatisticsRepository teamStatisticsRepository;

    @Autowired
    private FootballApiService footballApiService;

    @Autowired
    private TeamService teamService;
    
    @Autowired
    private LeagueService leagueService;

    @Autowired
    private ObjectMapper objectMapper;

    public void saveTeamStatisticsToDatabase(Long teamId, Long leagueId){
        try {
            String json = footballApiService.getTeamStatisticsByTeamIdAndLeagueId(teamId, leagueId);
            
            JsonNode rootNode   = objectMapper.readTree(json);
            JsonNode response   = rootNode.path("response");

            TeamStatistic teamStatistic = null;

            if (teamStatisticsRepository.existsByTeamIdAndLeagueId(response.path("team").path("id").asLong(), response.path("league").path("id").asLong())){
                teamStatistic = teamStatisticsRepository.findByTeamIdAndLeagueId(response.path("team").path("id").asLong(), response.path("league").path("id").asLong());
            }else {
                teamStatistic = new TeamStatistic();
            }

            teamStatistic.setTeamId(response.path("team").path("id").asLong());
            teamStatistic.setLeagueId(response.path("league").path("id").asLong());
            teamStatistic.setSeason(response.path("league").path("season").asLong());
            teamStatistic.setForm(response.path("form").asText());
            teamStatistic.setCleanSheetsHome(response.path("clean_sheet").path("home").asLong());
            teamStatistic.setCleanSheetsAway(response.path("clean_sheet").path("away").asLong());
            teamStatistic.setCleanSheetsTotal(response.path("clean_sheet").path("total").asLong());
            teamStatistic.setFailedToScoreHome(response.path("failed_to_score").path("home").asLong());
            teamStatistic.setFailedToScoreAway(response.path("failed_to_score").path("away").asLong());
            teamStatistic.setFailedToScoreTotal(response.path("failed_to_score").path("total").asLong());
            teamStatistic.setPenaltyScored(response.path("penalty").path("scored").path("total").asLong());
            teamStatistic.setPenaltyScoredPercentage(response.path("penalty").path("scored").path("percentage").asText());
            teamStatistic.setPenaltyMissed(response.path("penalty").path("missed").path("total").asLong());
            teamStatistic.setPenaltyMissedPercentage(response.path("penalty").path("missed").path("percentage").asText());

            FixtureStatistic fixtureStatistic = teamStatistic.getFixtureStatistic();
            if (fixtureStatistic == null) {
                fixtureStatistic = FixtureStatistic.builder()
                    .teamStatistic(teamStatistic)
                    .build();
                teamStatistic.setFixtureStatistic(fixtureStatistic);
            }
            fixtureStatistic.setPlayedHome(response.path("fixtures").path("played").path("home").asLong());
            fixtureStatistic.setPlayedAway(response.path("fixtures").path("played").path("away").asLong());
            fixtureStatistic.setPlayedTotal(response.path("fixtures").path("played").path("total").asLong());
            fixtureStatistic.setWinsHome(response.path("fixtures").path("wins").path("home").asLong());
            fixtureStatistic.setWinsAway(response.path("fixtures").path("wins").path("away").asLong());
            fixtureStatistic.setWinsTotal(response.path("fixtures").path("wins").path("total").asLong());
            fixtureStatistic.setDrawsHome(response.path("fixtures").path("draws").path("home").asLong());
            fixtureStatistic.setDrawsAway(response.path("fixtures").path("draws").path("away").asLong());
            fixtureStatistic.setDrawsTotal(response.path("fixtures").path("draws").path("total").asLong());
            fixtureStatistic.setLosesHome(response.path("fixtures").path("loses").path("home").asLong());
            fixtureStatistic.setLosesAway(response.path("fixtures").path("loses").path("away").asLong());
            fixtureStatistic.setLosesTotal(response.path("fixtures").path("loses").path("total").asLong());


            GoalStatistic goalStatistic = teamStatistic.getGoalStatistic();
            if (goalStatistic == null) {
                goalStatistic = GoalStatistic.builder()
                    .teamStatistic(teamStatistic)
                    .build();
                teamStatistic.setGoalStatistic(goalStatistic);
            }
            goalStatistic.setGoalsForHome(response.path("goals").path("for").path("total").path("home").asLong());
            goalStatistic.setGoalsForAway(response.path("goals").path("for").path("total").path("away").asLong());
            goalStatistic.setGoalsForTotal(response.path("goals").path("for").path("total").path("total").asLong());
            goalStatistic.setGoalsAgainstHome(response.path("goals").path("against").path("total").path("home").asLong());
            goalStatistic.setGoalsAgainstAway(response.path("goals").path("against").path("total").path("away").asLong());
            goalStatistic.setGoalsAgainstTotal(response.path("goals").path("against").path("total").path("total").asLong());
            goalStatistic.setAverageGoalsForHome(response.path("goals").path("for").path("average").path("home").asText());
            goalStatistic.setAverageGoalsForAway(response.path("goals").path("for").path("average").path("away").asText());
            goalStatistic.setAverageGoalsForTotal(response.path("goals").path("for").path("average").path("total").asText());
            goalStatistic.setAverageGoalsAgainstHome(response.path("goals").path("against").path("average").path("home").asText());
            goalStatistic.setAverageGoalsAgainstAway(response.path("goals").path("against").path("average").path("away").asText());
            goalStatistic.setAverageGoalsAgainstTotal(response.path("goals").path("against").path("average").path("total").asText());
            goalStatistic.setOver0_5ForGoals(response.path("goals").path("for").path("under_over").path("0.5").path("over").asLong());
            goalStatistic.setUnder0_5ForGoals(response.path("goals").path("for").path("under_over").path("0.5").path("under").asLong());
            goalStatistic.setOver1_5ForGoals(response.path("goals").path("for").path("under_over").path("1.5").path("over").asLong());
            goalStatistic.setUnder1_5ForGoals(response.path("goals").path("for").path("under_over").path("1.5").path("under").asLong());
            goalStatistic.setOver2_5ForGoals(response.path("goals").path("for").path("under_over").path("2.5").path("over").asLong());
            goalStatistic.setUnder2_5ForGoals(response.path("goals").path("for").path("under_over").path("2.5").path("under").asLong());
            goalStatistic.setOver3_5ForGoals(response.path("goals").path("for").path("under_over").path("3.5").path("over").asLong());
            goalStatistic.setUnder3_5ForGoals(response.path("goals").path("for").path("under_over").path("3.5").path("under").asLong());
            goalStatistic.setOver4_5ForGoals(response.path("goals").path("for").path("under_over").path("4.5").path("over").asLong());
            goalStatistic.setUnder4_5ForGoals(response.path("goals").path("for").path("under_over").path("4.5").path("under").asLong());
            goalStatistic.setOver0_5AgGoals(response.path("goals").path("against").path("under_over").path("0.5").path("over").asLong());
            goalStatistic.setUnder0_5AgGoals(response.path("goals").path("against").path("under_over").path("0.5").path("under").asLong());
            goalStatistic.setOver1_5AgGoals(response.path("goals").path("against").path("under_over").path("1.5").path("over").asLong());
            goalStatistic.setUnder1_5AgGoals(response.path("goals").path("against").path("under_over").path("1.5").path("under").asLong());
            goalStatistic.setOver2_5AgGoals(response.path("goals").path("against").path("under_over").path("2.5").path("over").asLong());
            goalStatistic.setUnder2_5AgGoals(response.path("goals").path("against").path("under_over").path("2.5").path("under").asLong());
            goalStatistic.setOver3_5AgGoals(response.path("goals").path("against").path("under_over").path("3.5").path("over").asLong());
            goalStatistic.setUnder3_5AgGoals(response.path("goals").path("against").path("under_over").path("3.5").path("under").asLong());
            goalStatistic.setOver4_5AgGoals(response.path("goals").path("against").path("under_over").path("4.5").path("over").asLong());
            goalStatistic.setUnder4_5AgGoals(response.path("goals").path("against").path("under_over").path("4.5").path("under").asLong());
            goalStatistic.setTotalForGoals0to15Min(response.path("goals").path("for").path("minute").path("0-15").path("total").asLong());
            goalStatistic.setAvgForGoals0to15Min(response.path("goals").path("for").path("minute").path("0-15").path("percentage").asText());
            goalStatistic.setTotalForGoals16to30Min(response.path("goals").path("for").path("minute").path("16-30").path("total").asLong());
            goalStatistic.setAvgForGoals16to30Min(response.path("goals").path("for").path("minute").path("16-30").path("percentage").asText());
            goalStatistic.setTotalForGoals31to45Min(response.path("goals").path("for").path("minute").path("31-45").path("total").asLong());
            goalStatistic.setAvgForGoals31to45Min(response.path("goals").path("for").path("minute").path("31-45").path("percentage").asText());
            goalStatistic.setTotalForGoals46to60Min(response.path("goals").path("for").path("minute").path("46-60").path("total").asLong());
            goalStatistic.setAvgForGoals46to60Min(response.path("goals").path("for").path("minute").path("46-60").path("percentage").asText());
            goalStatistic.setTotalForGoals61to75Min(response.path("goals").path("for").path("minute").path("61-75").path("total").asLong());
            goalStatistic.setAvgForGoals61to75Min(response.path("goals").path("for").path("minute").path("61-75").path("percentage").asText());
            goalStatistic.setTotalForGoals76to90Min(response.path("goals").path("for").path("minute").path("76-90").path("total").asLong());
            goalStatistic.setAvgForGoals76to90Min(response.path("goals").path("for").path("minute").path("76-90").path("percentage").asText());
            goalStatistic.setTotalForGoals91to105Min(response.path("goals").path("for").path("minute").path("91-105").path("total").asLong());
            goalStatistic.setAvgForGoals91to105Min(response.path("goals").path("for").path("minute").path("91-105").path("percentage").asText());
            goalStatistic.setTotalForGoals106to120Min(response.path("goals").path("for").path("minute").path("106-120").path("total").asLong());
            goalStatistic.setAvgForGoals106to120Min(response.path("goals").path("for").path("minute").path("106-120").path("percentage").asText());
            goalStatistic.setTotalAgGoals0to15Min(response.path("goals").path("against").path("minute").path("0-15").path("total").asLong());
            goalStatistic.setAvgAgGoals0to15Min(response.path("goals").path("against").path("minute").path("0-15").path("percentage").asText());
            goalStatistic.setTotalAgGoals16to30Min(response.path("goals").path("against").path("minute").path("16-30").path("total").asLong());
            goalStatistic.setAvgAgGoals16to30Min(response.path("goals").path("against").path("minute").path("16-30").path("percentage").asText());
            goalStatistic.setTotalAgGoals31to45Min(response.path("goals").path("against").path("minute").path("31-45").path("total").asLong());
            goalStatistic.setAvgAgGoals31to45Min(response.path("goals").path("against").path("minute").path("31-45").path("percentage").asText());
            goalStatistic.setTotalAgGoals46to60Min(response.path("goals").path("against").path("minute").path("46-60").path("total").asLong());
            goalStatistic.setAvgAgGoals46to60Min(response.path("goals").path("against").path("minute").path("46-60").path("percentage").asText());
            goalStatistic.setTotalAgGoals61to75Min(response.path("goals").path("against").path("minute").path("61-75").path("total").asLong());
            goalStatistic.setAvgAgGoals61to75Min(response.path("goals").path("against").path("minute").path("61-75").path("percentage").asText());
            goalStatistic.setTotalAgGoals76to90Min(response.path("goals").path("against").path("minute").path("76-90").path("total").asLong());
            goalStatistic.setAvgAgGoals76to90Min(response.path("goals").path("against").path("minute").path("76-90").path("percentage").asText());
            goalStatistic.setTotalAgGoals91to105Min(response.path("goals").path("against").path("minute").path("91-105").path("total").asLong());
            goalStatistic.setAvgAgGoals91to105Min(response.path("goals").path("against").path("minute").path("91-105").path("percentage").asText());
            goalStatistic.setTotalAgGoals106to120Min(response.path("goals").path("against").path("minute").path("106-120").path("total").asLong());
            goalStatistic.setAvgAgGoals106to120Min(response.path("goals").path("against").path("minute").path("106-120").path("percentage").asText());

            BiggestStatistic biggestStatistic = teamStatistic.getBiggestStatistic();
            if (biggestStatistic == null){
                biggestStatistic = BiggestStatistic.builder()
                .teamStatistic(teamStatistic)
                .build();
                teamStatistic.setBiggestStatistic(biggestStatistic);
            }
            biggestStatistic.setStreakWins(response.path("biggest").path("streak").path("wins").asLong());
            biggestStatistic.setStreakDraws(response.path("biggest").path("streak").path("draws").asLong());
            biggestStatistic.setStreakLoses(response.path("biggest").path("streak").path("loses").asLong());
            biggestStatistic.setWinsHome(response.path("biggest").path("wins").path("home").asText());
            biggestStatistic.setWinsAway(response.path("biggest").path("wins").path("away").asText());
            biggestStatistic.setLosesHome(response.path("biggest").path("loses").path("home").asText());
            biggestStatistic.setLosesAway(response.path("biggest").path("loses").path("away").asText());
            biggestStatistic.setGoalsForHome(response.path("biggest").path("goals").path("for").path("home").asLong());
            biggestStatistic.setGoalsForAway(response.path("biggest").path("goals").path("for").path("away").asLong());
            biggestStatistic.setGoalsAgainstHome(response.path("biggest").path("goals").path("against").path("home").asLong());
            biggestStatistic.setGoalsAgainstAway(response.path("biggest").path("goals").path("against").path("away").asLong());

            teamStatistic.setYellowCard0to15(response.path("cards").path("yellow").path("0-15").path("total").asLong());
            teamStatistic.setYellowCard16to30(response.path("cards").path("yellow").path("16-30").path("total").asLong());
            teamStatistic.setYellowCard31to45(response.path("cards").path("yellow").path("31-45").path("total").asLong());
            teamStatistic.setYellowCard46to60(response.path("cards").path("yellow").path("46-60").path("total").asLong());
            teamStatistic.setYellowCard61to75(response.path("cards").path("yellow").path("61-75").path("total").asLong());
            teamStatistic.setYellowCard76to90(response.path("cards").path("yellow").path("76-90").path("total").asLong());
            teamStatistic.setYellowCard91to105(response.path("cards").path("yellow").path("91-105").path("total").asLong());
            teamStatistic.setYellowCard106to120(response.path("cards").path("yellow").path("106-120").path("total").asLong());

            teamStatistic.setPerYellowCard0to15(response.path("cards").path("yellow").path("0-15").path("percentage").asLong());
            teamStatistic.setPerYellowCard16to30(response.path("cards").path("yellow").path("16-30").path("percentage").asLong());
            teamStatistic.setPerYellowCard31to45(response.path("cards").path("yellow").path("31-45").path("percentage").asLong());
            teamStatistic.setPerYellowCard46to60(response.path("cards").path("yellow").path("46-60").path("percentage").asLong());
            teamStatistic.setPerYellowCard61to75(response.path("cards").path("yellow").path("61-75").path("percentage").asLong());
            teamStatistic.setPerYellowCard76to90(response.path("cards").path("yellow").path("76-90").path("percentage").asLong());
            teamStatistic.setPerYellowCard91to105(response.path("cards").path("yellow").path("91-105").path("percentage").asLong());
            teamStatistic.setPerYellowCard106to120(response.path("cards").path("yellow").path("106-120").path("percentage").asLong());

            teamStatistic.setRedCard0to15(response.path("cards").path("red").path("0-15").path("total").asLong());
            teamStatistic.setRedCard16to30(response.path("cards").path("red").path("16-30").path("total").asLong());
            teamStatistic.setRedCard31to45(response.path("cards").path("red").path("31-45").path("total").asLong());
            teamStatistic.setRedCard46to60(response.path("cards").path("red").path("46-60").path("total").asLong());
            teamStatistic.setRedCard61to75(response.path("cards").path("red").path("61-75").path("total").asLong());
            teamStatistic.setRedCard76to90(response.path("cards").path("red").path("76-90").path("total").asLong());
            teamStatistic.setRedCard91to105(response.path("cards").path("red").path("91-105").path("total").asLong());
            teamStatistic.setRedCard106to120(response.path("cards").path("red").path("106-120").path("total").asLong());
            
            teamStatistic.setPerRedCard0to15(response.path("cards").path("red").path("0-15").path("percentage").asLong());
            teamStatistic.setPerRedCard16to30(response.path("cards").path("red").path("16-30").path("percentage").asLong());
            teamStatistic.setPerRedCard31to45(response.path("cards").path("red").path("31-45").path("percentage").asLong());
            teamStatistic.setPerRedCard46to60(response.path("cards").path("red").path("46-60").path("percentage").asLong());
            teamStatistic.setPerRedCard61to75(response.path("cards").path("red").path("61-75").path("percentage").asLong());
            teamStatistic.setPerRedCard76to90(response.path("cards").path("red").path("76-90").path("percentage").asLong());
            teamStatistic.setPerRedCard91to105(response.path("cards").path("red").path("91-105").path("percentage").asLong());
            teamStatistic.setPerRedCard106to120(response.path("cards").path("red").path("106-120").path("percentage").asLong());

            teamStatisticsRepository.save(teamStatistic);

            TimeUnit.SECONDS.sleep(2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String addTeamStatistics(AddTeamStatisticsRequest request){
        try{
            Long teamId     = teamService.getTeamIdByName(request.getTeamName());
            Long leagueId   = leagueService.getLeagueIdByName(request.getLeagueName());
            saveTeamStatisticsToDatabase(teamId, leagueId);
            return "Team Statistic saved successfully!";
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return "Error: " + e.getMessage();
        }
    
    }
}
