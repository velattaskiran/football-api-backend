package com.taskiran.footballapibackend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddFixturesRequest {
    String leagueName;
    Long season;    

    public AddFixturesRequest(String leagueName, Long season){
        this.leagueName = leagueName;
        this.season     = season;
    }
}
