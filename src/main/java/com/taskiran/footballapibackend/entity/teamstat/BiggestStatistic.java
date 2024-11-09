package com.taskiran.footballapibackend.entity.teamstat;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "biggest_statistics")
public class BiggestStatistic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "streak_wins")
    private Long streakWins;

    @Column(name = "streak_draws")
    private Long streakDraws;

    @Column(name = "streak_loses")
    private Long streakLoses;

    @Column(name = "wins_home")
    private String winsHome;

    @Column(name = "wins_away")
    private String winsAway;

    @Column(name = "loses_home")
    private String losesHome;

    @Column(name = "loses_away")
    private String losesAway;

    @Column(name = "goals_for_home")
    private Long goalsForHome;
    
    @Column(name = "goals_for_away")
    private Long goalsForAway;

    @Column(name = "goals_against_home")
    private Long goalsAgainstHome;
    
    @Column(name = "goals_against_away")
    private Long goalsAgainstAway;

    // TeamStatistic ile ilişki
    @OneToOne
    @JoinColumn(name = "team_statistic_id", referencedColumnName = "statistic_id")
    private TeamStatistic teamStatistic;
    
}
