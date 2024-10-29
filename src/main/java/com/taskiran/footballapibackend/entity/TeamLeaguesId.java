package com.taskiran.footballapibackend.entity;

import java.io.Serializable;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class TeamLeaguesId implements Serializable {
    private Long team;
    private Long league;
}
