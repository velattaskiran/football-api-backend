package com.taskiran.footballapibackend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.taskiran.footballapibackend.dto.AddPlayerStatisticRequest;
import com.taskiran.footballapibackend.dto.AddTeamStatisticsRequest;
import com.taskiran.footballapibackend.repository.LeagueRepository;
import com.taskiran.footballapibackend.repository.TeamLeaguesRepository;
import com.taskiran.footballapibackend.repository.TeamRepository;

@Service
public class TaskService {

    @Autowired 
    private RestTemplate restTemplate;

    @Autowired
    private LeagueRepository leagueRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private TeamLeaguesRepository teamLeaguesRepository;

    @Autowired
    private LeagueService leagueService;

    public void executeTasks() {
        saveTeamStatistics();
        savePlayerStatistics();
    }

    private void saveTeamStatistics() {

        String url = "http://localhost:8081/saveTeamStatistics";
        List<String> leagueNames = leagueRepository.findAllLeagueNames();

        for (String leagueName : leagueNames){            
            Long leagueId   = leagueService.getLeagueIdByName(leagueName);
            List<Long> teamIds = teamLeaguesRepository.findAllTeamIdsByLeagueId(leagueId);
            for(Long teamId : teamIds){
                String teamName = teamRepository.findTeamNameById(teamId);
                AddTeamStatisticsRequest request = new AddTeamStatisticsRequest(teamName, leagueName);
                String response = restTemplate.postForObject(url, request, String.class);
                System.out.println(teamName + " --- Response :" + response);
            }
        }
    }

    private void savePlayerStatistics(){        
        String url = "http://localhost:8081/savePlayerStatisticsByLeagueName";
        List<String> leagueNames = leagueRepository.findAllLeagueNames();

        for (String leagueName : leagueNames){
            String teamName = "x";
            AddPlayerStatisticRequest request = new AddPlayerStatisticRequest(leagueName, teamName);
            String response = restTemplate.postForObject(url, request, String.class);
            System.out.println(leagueName + " --- Response :" + response);
        }

    }
}
