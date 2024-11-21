package com.taskiran.footballapibackend.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddTeamRequest {
    String leagueName;
    Long season;
}
