package com.taskiran.footballapibackend.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "players_statistics")
public class PlayerStatistic {    
    @Id
    @Column(name = "statistic_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "player_id")
    private Long playerId;

    @Column(name = "team_id")
    private Long teamId;

    @Column(name = "league_id")
    private Long leagueId;

    @Column(name = "season")
    private Long season;

    @Column(name = "appearences")
    private Long appearences;

    @Column(name = "lineups")
    private Long lineups;

    @Column(name = "minutes")
    private Long minutes;
    
    @Column(name = "rating")
    private Long rating;
    
    @Column(name = "substitutes_in")
    private Long substitutesIn;
    
    @Column(name = "substitutes_out")
    private Long substitutesOut;
    
    @Column(name = "substitutes_bench")
    private Long substitutesBench;
    
    @Column(name = "shots_total")
    private Long shotsTotal;
    
    @Column(name = "shots_on")
    private Long shotsOn;
    
    @Column(name = "goals")
    private Long goals;
    
    @Column(name = "goal_conceded")
    private Long goalConceded;
    
    @Column(name = "assists")
    private Long assists;
    
    @Column(name = "goal_saves")
    private Long goalSaves;

    @Column(name = "passes")
    private Long passes;
    
    @Column(name = "passes_key")
    private Long passesKey;
    
    @Column(name = "passes_accurracy")
    private Long passesAccurracy;
    
    @Column(name = "tackles")
    private Long tackles;
    
    @Column(name = "tackles_blocks")
    private Long tacklesBlocks;
    
    @Column(name = "tackles_interceptions")
    private Long tacklesInterceptions;
    
    @Column(name = "duels")
    private Long duels;
    
    @Column(name = "duels_won")
    private Long duelsWon;
    
    @Column(name = "dribbles_attempts")
    private Long dribblesAttempts;
    
    @Column(name = "dribbles_success")
    private Long dribblesSuccess;
    
    @Column(name = "fouls_drawn")
    private Long foulsDrawn;
    
    @Column(name = "fouls_committed")
    private Long foulsCommitted;
    
    @Column(name = "cards_yellow")
    private Long cardsYellow;
    
    @Column(name = "cards_yellow_red")
    private Long cardsYellowRed;
    
    @Column(name = "cards_red")
    private Long cardsRed;
    
    @Column(name = "penalty_won")
    private Long penaltyWon;
    
    @Column(name = "penalty_committed")
    private Long penaltyCommitted;
    
    @Column(name = "penalty_scored")
    private Long penaltyScored;
    
    @Column(name = "penalty_missed")
    private Long penaltyMissed;
    
    @Column(name = "penalty_saved")
    private Long penaltySaved;
}
