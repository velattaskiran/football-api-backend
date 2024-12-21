package com.taskiran.footballapibackend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddPlayerStatisticRequest {
    String leagueName;
    String teamName;    

    public AddPlayerStatisticRequest(String leagueName, String teamName){
        this.leagueName = leagueName;
        this.teamName   = teamName;
    }
}
