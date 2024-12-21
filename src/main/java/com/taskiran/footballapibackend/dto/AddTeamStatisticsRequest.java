package com.taskiran.footballapibackend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddTeamStatisticsRequest {
    String teamName;
    String leagueName;

    public AddTeamStatisticsRequest(String teamName, String leagueName){
        this.teamName   = teamName;
        this.leagueName = leagueName;
    }
}
