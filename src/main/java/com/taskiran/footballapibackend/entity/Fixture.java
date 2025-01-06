package com.taskiran.footballapibackend.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "fixtures")
public class Fixture {
    @Id
    @Column(name = "fixture_id", nullable = false)
    private Long fixtureId;

    @Column(name = "referee")
    private String referee;

    @Column(name = "timestamp")
    private Long timestamp;

    @Embedded
    private Venue venue;

    @Embedded
    private Status status;

    @Column(name = "league_id")
    private Long leagueId;

    @Column(name = "league_name")
    private String leagueName;

    @Column(name = "season")
    private Long season;

    @Column(name = "round")
    private String round;
    
    @Column(name = "home_team_id")
    private Long homeTeamId;

    @Column(name = "home_team_name")
    private String homeTeamName;

    @Column(name = "home_team_winner")
    private Boolean homeTeamWinner;

    @Column(name = "away_team_id")
    private Long awayTeamId;

    @Column(name = "away_team_name")
    private String awayTeamName;

    @Column(name = "away_team_winner")
    private Boolean awayTeamWinner;

    @Embedded
    private Goals goals;

    @Embedded
    private Score score;

    @Getter
    @Setter
    @Embeddable
    public static class Venue {
        private Long id;
        private String name;
        private String city;
    }

    @Getter
    @Setter
    @Embeddable
    public static class Status {
        private String longStatus;
        private String shortStatus;
        private Integer elapsed;
    }

    @Getter
    @Setter
    @Embeddable
    public static class Goals {
        private Integer home;
        private Integer away;
    }


    @Getter
    @Setter
    @Embeddable
    public static class Score {
        private Integer halftimeHome;
        private Integer halftimeAway;
        private Integer fulltimeHome;
        private Integer fulltimeAway;
        private Integer extratimeHome;
        private Integer extratimeAway;
        private Integer penaltyHome;
        private Integer penaltyAway;        
    }
}
