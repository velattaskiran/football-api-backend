package com.taskiran.footballapibackend.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddTeamStatisticsRequest {
    String teamName;
    String leagueName;
}
