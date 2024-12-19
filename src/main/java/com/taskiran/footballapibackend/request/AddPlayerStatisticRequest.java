package com.taskiran.footballapibackend.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddPlayerStatisticRequest {
    String leagueName;
    String teamName;
}
