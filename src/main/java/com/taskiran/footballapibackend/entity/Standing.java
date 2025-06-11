package com.taskiran.footballapibackend.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "standings")
public class Standing {

    @Id
    @Column(name = "standing_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "league_id", nullable = false)
    private Long leagueId;

    @Column(name = "league_name", nullable = false)
    private String leagueName;

    @Column(name = "team_id", nullable = false)
    private Long teamId;

    @Column(name = "team_name", nullable = false)
    private String teamName;

    @Column(name = "team_logo", nullable = false)
    private String teamLogo;

    @Column(name = "rank")
    private Integer rank;

    @Column(name = "points")
    private Integer points;

    @Column(name = "goals_difference")
    private Integer goalsDiff;

    @Column(name = "form")
    private String form;

    @Column(name = "description")
    private String description;

    @Column(name = "played")
    private Integer played;

    @Column(name = "wins")
    private Integer win;

    @Column(name = "draws")
    private Integer draw;

    @Column(name = "losses")
    private Integer lose;

    @Column(name = "goals_for")
    private Integer goalsFor;

    @Column(name = "goals_against")
    private Integer goalsAgainst;

    @Column(name = "home_played")
    private Integer homePlayed;

    @Column(name = "home_wins")
    private Integer homeWin;

    @Column(name = "home_draws")
    private Integer homeDraw;

    @Column(name = "home_losses")
    private Integer homeLose;

    @Column(name = "home_goals_for")
    private Integer homeGoalsFor;

    @Column(name = "home_goals_against")
    private Integer homeGoalsAgainst;

    @Column(name = "away_played")
    private Integer awayPlayed;

    @Column(name = "away_wins")
    private Integer awayWin;

    @Column(name = "away_draws")
    private Integer awayDraw;

    @Column(name = "away_losses")
    private Integer awayLose;

    @Column(name = "away_goals_for")
    private Integer awayGoalsFor;

    @Column(name = "away_goals_against")
    private Integer awayGoalsAgainst;

    @Column(name = "last_updated")
    private String update;
}

