package com.taskiran.footballapibackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.taskiran.footballapibackend.dto.AddFixturesRequest;
import com.taskiran.footballapibackend.entity.Fixture;
import com.taskiran.footballapibackend.repository.FixtureRepository;

@Service
public class FixtureService {

    @Autowired
    private FootballApiService footballApiService;

    @Autowired      
    private FixtureRepository fixtureRepository;

    @Autowired
    private LeagueService leagueService;

    @Autowired
    ObjectMapper objectMapper = new ObjectMapper();

    public void saveFixtureToDatabase(Long leagueId, Long season){
        try{
            String json = footballApiService.getFixturesByLeagueId(leagueId, season);

            JsonNode rootNode       = objectMapper.readTree(json);
            JsonNode fixturesArray  = rootNode.path("response");

            Fixture fixture = null;

            if (fixturesArray.isArray() && fixturesArray.size() > 0){
                for (JsonNode fixtureNode : fixturesArray){

                    if (fixtureRepository.existsByFixtureId(fixtureNode.path("fixture").path("id").asLong())){
                        fixture = fixtureRepository.findByFixtureId(fixtureNode.path("fixture").path("id").asLong());
                    } else {
                        fixture = new Fixture();
                    }

                    fixture.setFixtureId(fixtureNode.path("fixture").path("id").asLong());
                    fixture.setReferee(fixtureNode.path("fixture").path("referee").asText());
                    fixture.setTimestamp(fixtureNode.path("fixture").path("timestamp").asLong());

                    JsonNode venueNode = fixtureNode.path("fixture").path("venue");
                    if (venueNode != null && !venueNode.isMissingNode()) {
                        Fixture.Venue venue = new Fixture.Venue();
                        venue.setId(venueNode.path("id").asLong());
                        venue.setName(venueNode.path("name").asText());
                        venue.setCity(venueNode.path("city").asText());

                        fixture.setVenue(venue);
                    }

                    JsonNode statusNode = fixtureNode.path("fixture").path("status");
                    if (statusNode != null && !statusNode.isMissingNode()) {
                        Fixture.Status status = new Fixture.Status();
                        status.setLongStatus(statusNode.path("long").asText());
                        status.setShortStatus(statusNode.path("short").asText());
                        status.setElapsed(statusNode.path("elapsed").asInt());

                        fixture.setStatus(status);
                    }
                    
                    fixture.setLeagueId(fixtureNode.path("league").path("id").asLong());
                    fixture.setLeagueName(fixtureNode.path("league").path("name").asText());
                    fixture.setSeason(fixtureNode.path("league").path("season").asLong());
                    fixture.setRound(fixtureNode.path("league").path("round").asText());
                    fixture.setHomeTeamId(fixtureNode.path("teams").path("home").path("id").asLong());
                    fixture.setHomeTeamName(fixtureNode.path("teams").path("home").path("name").asText());
                    fixture.setHomeTeamWinner(fixtureNode.path("teams").path("home").path("winner").asBoolean());
                    fixture.setAwayTeamId(fixtureNode.path("teams").path("away").path("id").asLong());
                    fixture.setAwayTeamName(fixtureNode.path("teams").path("away").path("name").asText());
                    fixture.setAwayTeamWinner(fixtureNode.path("teams").path("away").path("winner").asBoolean());

                    JsonNode goalsNode = fixtureNode.path("goals");
                    if(goalsNode != null && !goalsNode.isMissingNode()){
                        Fixture.Goals goals = new Fixture.Goals();
                        goals.setHome(goalsNode.path("home").asInt());
                        goals.setAway(goalsNode.path("away").asInt());

                        fixture.setGoals(goals);
                    }

                    JsonNode scoreNode = fixtureNode.path("score");
                    if(scoreNode != null && !scoreNode.isMissingNode()){
                        Fixture.Score score = new Fixture.Score();
                        score.setHalftimeHome(scoreNode.path("halftime").path("home").asInt());
                        score.setHalftimeAway(scoreNode.path("halftime").path("away").asInt());
                        score.setFulltimeHome(scoreNode.path("fulltime").path("home").asInt());
                        score.setFulltimeAway(scoreNode.path("fulltime").path("away").asInt());
                        score.setExtratimeHome(scoreNode.path("extratime").path("home").asInt());
                        score.setExtratimeAway(scoreNode.path("extratime").path("away").asInt());
                        score.setPenaltyHome(scoreNode.path("penalty").path("home").asInt());
                        score.setPenaltyAway(scoreNode.path("penalty").path("away").asInt());

                        fixture.setScore(score);
                    }

                    fixtureRepository.save(fixture);
                }
            }else{
                throw new Exception("No fixtures found for the given league ID and season");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String addFixturesByLeagueName(AddFixturesRequest request) {
        Long leagueId = leagueService.getLeagueIdByName(request.getLeagueName());
        saveFixtureToDatabase(leagueId, request.getSeason());
        return "Fixtures saved successfully!";
    }

}
