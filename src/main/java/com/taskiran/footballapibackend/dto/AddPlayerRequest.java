package com.taskiran.footballapibackend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddPlayerRequest {
    String leagueName;
    String teamName;
}
