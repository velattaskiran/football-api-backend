package com.taskiran.footballapibackend.service;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.taskiran.footballapibackend.dto.AddTeamStatisticsRequest;
import com.taskiran.footballapibackend.entity.teamstat.BiggestStatistic;
import com.taskiran.footballapibackend.entity.teamstat.FixtureStatistic;
import com.taskiran.footballapibackend.entity.teamstat.GoalStatistic;
import com.taskiran.footballapibackend.entity.teamstat.MinuteStatistic;
import com.taskiran.footballapibackend.entity.teamstat.TeamStatistic;
import com.taskiran.footballapibackend.entity.teamstat.UnderOverStatistic;
import com.taskiran.footballapibackend.entity.teamstat.UnderOverType;
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

            TeamStatistic teamStatistic = new TeamStatistic();

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
            
            FixtureStatistic fixtureStatistic = FixtureStatistic.builder()
                .playedHome(response.path("fixtures").path("played").path("home").asLong())
                .playedAway(response.path("fixtures").path("played").path("away").asLong())
                .playedTotal(response.path("fixtures").path("played").path("total").asLong())
                .winsHome(response.path("fixtures").path("wins").path("home").asLong())
                .winsAway(response.path("fixtures").path("wins").path("away").asLong())
                .winsTotal(response.path("fixtures").path("wins").path("total").asLong())
                .drawsHome(response.path("fixtures").path("draws").path("home").asLong())
                .drawsAway(response.path("fixtures").path("draws").path("away").asLong())
                .drawsTotal(response.path("fixtures").path("draws").path("total").asLong())
                .losesHome(response.path("fixtures").path("loses").path("home").asLong())
                .losesAway(response.path("fixtures").path("loses").path("away").asLong())
                .losesTotal(response.path("fixtures").path("loses").path("total").asLong())
                .teamStatistic(teamStatistic)
                .build();
            teamStatistic.setFixtureStatistic(fixtureStatistic);

            GoalStatistic goalStatistic = GoalStatistic.builder()
                .goalsForHome(response.path("goals").path("for").path("total").path("home").asLong())
                .goalsForAway(response.path("goals").path("for").path("total").path("away").asLong())
                .goalsForTotal(response.path("goals").path("for").path("total").path("total").asLong())
                .goalsAgainstHome(response.path("goals").path("against").path("total").path("home").asLong())
                .goalsAgainstAway(response.path("goals").path("against").path("total").path("away").asLong())
                .goalsAgainstTotal(response.path("goals").path("against").path("total").path("total").asLong())
                .averageGoalsForHome(response.path("goals").path("for").path("average").path("home").asText())
                .averageGoalsForAway(response.path("goals").path("for").path("average").path("away").asText())
                .averageGoalsForTotal(response.path("goals").path("for").path("average").path("total").asText())
                .averageGoalsAgainstHome(response.path("goals").path("against").path("average").path("home").asText())
                .averageGoalsAgainstAway(response.path("goals").path("against").path("average").path("away").asText())
                .averageGoalsAgainstTotal(response.path("goals").path("against").path("average").path("total").asText())
                .teamStatistic(teamStatistic)
                .build();
            UnderOverStatistic for_uo1 = UnderOverStatistic.builder()
                .overUnderValue("0.5")
                .over(response.path("goals").path("for").path("under_over").path("0.5").path("over").asLong())
                .under(response.path("goals").path("for").path("under_over").path("0.5").path("under").asLong())
                .type(UnderOverType.FOR)
                .goalStatistic(goalStatistic)
                .build();
            UnderOverStatistic for_uo2 = UnderOverStatistic.builder()
                .overUnderValue("1.5")
                .over(response.path("goals").path("for").path("under_over").path("1.5").path("over").asLong())
                .under(response.path("goals").path("for").path("under_over").path("1.5").path("under").asLong())
                .type(UnderOverType.FOR)
                .goalStatistic(goalStatistic)
                .build();
            UnderOverStatistic for_uo3 = UnderOverStatistic.builder()
                .overUnderValue("2.5")
                .over(response.path("goals").path("for").path("under_over").path("2.5").path("over").asLong())
                .under(response.path("goals").path("for").path("under_over").path("2.5").path("under").asLong())
                .type(UnderOverType.FOR)
                .goalStatistic(goalStatistic)
                .build();
            UnderOverStatistic for_uo4 = UnderOverStatistic.builder()
                .overUnderValue("3.5")
                .over(response.path("goals").path("for").path("under_over").path("3.5").path("over").asLong())
                .under(response.path("goals").path("for").path("under_over").path("3.5").path("under").asLong())
                .type(UnderOverType.FOR)
                .goalStatistic(goalStatistic)
                .build();
            UnderOverStatistic for_uo5 = UnderOverStatistic.builder()
                .overUnderValue("4.5")
                .over(response.path("goals").path("for").path("under_over").path("4.5").path("over").asLong())
                .under(response.path("goals").path("for").path("under_over").path("4.5").path("under").asLong())
                .type(UnderOverType.FOR)
                .goalStatistic(goalStatistic)
                .build();                
            UnderOverStatistic ag_uo1 = UnderOverStatistic.builder()
                .overUnderValue("0.5")
                .over(response.path("goals").path("against").path("under_over").path("0.5").path("over").asLong())
                .under(response.path("goals").path("against").path("under_over").path("0.5").path("under").asLong())
                .type(UnderOverType.AGAINST)
                .goalStatistic(goalStatistic)
                .build();
            UnderOverStatistic ag_uo2 = UnderOverStatistic.builder()
                .overUnderValue("1.5")
                .over(response.path("goals").path("against").path("under_over").path("1.5").path("over").asLong())
                .under(response.path("goals").path("against").path("under_over").path("1.5").path("under").asLong())
                .type(UnderOverType.AGAINST)
                .goalStatistic(goalStatistic)
                .build();
            UnderOverStatistic ag_uo3 = UnderOverStatistic.builder()
                .overUnderValue("2.5")
                .over(response.path("goals").path("against").path("under_over").path("2.5").path("over").asLong())
                .under(response.path("goals").path("against").path("under_over").path("2.5").path("under").asLong())
                .type(UnderOverType.AGAINST)
                .goalStatistic(goalStatistic)
                .build();
            UnderOverStatistic ag_uo4 = UnderOverStatistic.builder()
                .overUnderValue("3.5")
                .over(response.path("goals").path("against").path("under_over").path("3.5").path("over").asLong())
                .under(response.path("goals").path("against").path("under_over").path("3.5").path("under").asLong())
                .type(UnderOverType.AGAINST)
                .goalStatistic(goalStatistic)
                .build();
            UnderOverStatistic ag_uo5 = UnderOverStatistic.builder()
                .overUnderValue("4.5")
                .over(response.path("goals").path("against").path("under_over").path("4.5").path("over").asLong())
                .under(response.path("goals").path("against").path("under_over").path("4.5").path("under").asLong())
                .type(UnderOverType.AGAINST)
                .goalStatistic(goalStatistic)
                .build();
            goalStatistic.setUnderOverStatistics(Arrays.asList(for_uo1, for_uo2, for_uo3, for_uo4, for_uo5, ag_uo1, ag_uo2, ag_uo3, ag_uo4, ag_uo5));

            MinuteStatistic for_m1 = MinuteStatistic.builder()
                .minuteRange("0-15")
                .totalGoals(response.path("goals").path("for").path("minute").path("0-15").path("total").asLong())
                .percentage(response.path("goals").path("for").path("minute").path("0-15").path("percentage").asText())
                .type(UnderOverType.FOR)
                .goalStatistic(goalStatistic)
                .build();
            MinuteStatistic for_m2 = MinuteStatistic.builder()
                .minuteRange("16-30")
                .totalGoals(response.path("goals").path("for").path("minute").path("16-30").path("total").asLong())
                .percentage(response.path("goals").path("for").path("minute").path("16-30").path("percentage").asText())
                .type(UnderOverType.FOR)
                .goalStatistic(goalStatistic)
                .build();
            MinuteStatistic for_m3 = MinuteStatistic.builder()
                .minuteRange("31-45")
                .totalGoals(response.path("goals").path("for").path("minute").path("31-45").path("total").asLong())
                .percentage(response.path("goals").path("for").path("minute").path("31-45").path("percentage").asText())
                .type(UnderOverType.FOR)
                .goalStatistic(goalStatistic)
                .build();
            MinuteStatistic for_m4 = MinuteStatistic.builder()
                .minuteRange("46-60")
                .totalGoals(response.path("goals").path("for").path("minute").path("46-60").path("total").asLong())
                .percentage(response.path("goals").path("for").path("minute").path("46-60").path("percentage").asText())
                .type(UnderOverType.FOR)
                .goalStatistic(goalStatistic)
                .build();
            MinuteStatistic for_m5 = MinuteStatistic.builder()
                .minuteRange("61-75")
                .totalGoals(response.path("goals").path("for").path("minute").path("61-75").path("total").asLong())
                .percentage(response.path("goals").path("for").path("minute").path("61-75").path("percentage").asText())
                .type(UnderOverType.FOR)
                .goalStatistic(goalStatistic)
                .build();
            MinuteStatistic for_m6 = MinuteStatistic.builder()
                .minuteRange("76-90")
                .totalGoals(response.path("goals").path("for").path("minute").path("61-75").path("total").asLong())
                .percentage(response.path("goals").path("for").path("minute").path("61-75").path("percentage").asText())
                .type(UnderOverType.FOR)
                .goalStatistic(goalStatistic)
                .build();
            MinuteStatistic for_m7 = MinuteStatistic.builder()
                .minuteRange("91-105")
                .totalGoals(response.path("goals").path("for").path("minute").path("61-75").path("total").asLong())
                .percentage(response.path("goals").path("for").path("minute").path("61-75").path("percentage").asText())
                .type(UnderOverType.FOR)
                .goalStatistic(goalStatistic)
                .build();
            MinuteStatistic for_m8 = MinuteStatistic.builder()
                .minuteRange("106-120")
                .totalGoals(response.path("goals").path("for").path("minute").path("61-75").path("total").asLong())
                .percentage(response.path("goals").path("for").path("minute").path("61-75").path("percentage").asText())
                .type(UnderOverType.FOR)
                .goalStatistic(goalStatistic)
                .build();            
            MinuteStatistic ag_m1 = MinuteStatistic.builder()
                .minuteRange("0-15")
                .totalGoals(response.path("goals").path("against").path("minute").path("0-15").path("total").asLong())
                .percentage(response.path("goals").path("against").path("minute").path("0-15").path("percentage").asText())
                .type(UnderOverType.AGAINST)
                .goalStatistic(goalStatistic)
                .build();
            MinuteStatistic ag_m2 = MinuteStatistic.builder()
                .minuteRange("16-30")
                .totalGoals(response.path("goals").path("against").path("minute").path("16-30").path("total").asLong())
                .percentage(response.path("goals").path("against").path("minute").path("16-30").path("percentage").asText())
                .type(UnderOverType.AGAINST)
                .goalStatistic(goalStatistic)
                .build();
            MinuteStatistic ag_m3 = MinuteStatistic.builder()
                .minuteRange("31-45")
                .totalGoals(response.path("goals").path("against").path("minute").path("31-45").path("total").asLong())
                .percentage(response.path("goals").path("against").path("minute").path("31-45").path("percentage").asText())
                .type(UnderOverType.AGAINST)
                .goalStatistic(goalStatistic)
                .build();
            MinuteStatistic ag_m4 = MinuteStatistic.builder()
                .minuteRange("46-60")
                .totalGoals(response.path("goals").path("against").path("minute").path("46-60").path("total").asLong())
                .percentage(response.path("goals").path("against").path("minute").path("46-60").path("percentage").asText())
                .type(UnderOverType.AGAINST)
                .goalStatistic(goalStatistic)
                .build();
            MinuteStatistic ag_m5 = MinuteStatistic.builder()
                .minuteRange("61-75")
                .totalGoals(response.path("goals").path("against").path("minute").path("61-75").path("total").asLong())
                .percentage(response.path("goals").path("against").path("minute").path("61-75").path("percentage").asText())
                .type(UnderOverType.AGAINST)
                .goalStatistic(goalStatistic)
                .build();
            MinuteStatistic ag_m6 = MinuteStatistic.builder()
                .minuteRange("76-90")
                .totalGoals(response.path("goals").path("against").path("minute").path("61-75").path("total").asLong())
                .percentage(response.path("goals").path("against").path("minute").path("61-75").path("percentage").asText())
                .type(UnderOverType.AGAINST)
                .goalStatistic(goalStatistic)
                .build();
            MinuteStatistic ag_m7 = MinuteStatistic.builder()
                .minuteRange("91-105")
                .totalGoals(response.path("goals").path("against").path("minute").path("61-75").path("total").asLong())
                .percentage(response.path("goals").path("against").path("minute").path("61-75").path("percentage").asText())
                .type(UnderOverType.AGAINST)
                .goalStatistic(goalStatistic)
                .build();
            MinuteStatistic ag_m8 = MinuteStatistic.builder()
                .minuteRange("106-120")
                .totalGoals(response.path("goals").path("against").path("minute").path("61-75").path("total").asLong())
                .percentage(response.path("goals").path("against").path("minute").path("61-75").path("percentage").asText())
                .type(UnderOverType.AGAINST)
                .goalStatistic(goalStatistic)
                .build();
            goalStatistic.setMinuteStatistics(Arrays.asList(for_m1, for_m2, for_m3, for_m4, for_m5, for_m6, for_m7, for_m8,
                                                            ag_m1, ag_m2, ag_m3, ag_m4, ag_m5, ag_m6, ag_m7, ag_m8));
            teamStatistic.setGoalStatistic(goalStatistic);

            BiggestStatistic biggestStatistic = BiggestStatistic.builder()
                .streakWins(response.path("biggest").path("streak").path("wins").asLong())
                .streakDraws(response.path("biggest").path("streak").path("draws").asLong())
                .streakLoses(response.path("biggest").path("streak").path("loses").asLong())
                .winsHome(response.path("biggest").path("wins").path("home").asText())
                .winsAway(response.path("biggest").path("wins").path("away").asText())
                .losesHome(response.path("biggest").path("loses").path("home").asText())
                .losesAway(response.path("biggest").path("loses").path("away").asText())
                .goalsForHome(response.path("biggest").path("goals").path("for").path("home").asLong())
                .goalsForAway(response.path("biggest").path("goals").path("for").path("away").asLong())
                .goalsAgainstHome(response.path("biggest").path("goals").path("against").path("home").asLong())
                .goalsAgainstAway(response.path("biggest").path("goals").path("against").path("away").asLong())
                .build();
            teamStatistic.setBiggestStatistic(biggestStatistic);

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

            if (teamStatisticsRepository.existsByTeamIdAndLeagueId(teamStatistic.getTeamId(), teamStatistic.getLeagueId())){
                TeamStatistic existingStatistic = teamStatisticsRepository.findByTeamIdAndLeagueId(teamStatistic.getTeamId(), teamStatistic.getLeagueId());
                
                Long repeatId = existingStatistic.getId();                
                teamStatisticsRepository.deleteById(repeatId);

                // ID'yi koruyarak, tüm alanları yeni nesneden güncelle
                teamStatistic.setId(repeatId);
                
                // Güncellenmiş nesneyi kaydet
                teamStatisticsRepository.save(teamStatistic);
            }else {
                teamStatisticsRepository.save(teamStatistic);
            }
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
