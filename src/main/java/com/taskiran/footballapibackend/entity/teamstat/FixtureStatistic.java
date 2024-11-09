package com.taskiran.footballapibackend.entity.teamstat;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "fixture_statistics")
public class FixtureStatistic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "played_home")
    private Long playedHome;

    @Column(name = "played_away")
    private Long playedAway;

    @Column(name = "played_total")
    private Long playedTotal;

    @Column(name = "wins_home")
    private Long winsHome;

    @Column(name = "wins_away")
    private Long winsAway;

    @Column(name = "wins_total")
    private Long winsTotal;

    @Column(name = "draws_home")
    private Long drawsHome;

    @Column(name = "draws_away")
    private Long drawsAway;

    @Column(name = "draws_total")
    private Long drawsTotal;

    @Column(name = "loses_home")
    private Long losesHome;

    @Column(name = "loses_away")
    private Long losesAway;

    @Column(name = "loses_total")
    private Long losesTotal;

    // TeamStatistic ile ilişki
    @OneToOne
    @JoinColumn(name = "team_statistic_id", referencedColumnName = "statistic_id")
    private TeamStatistic teamStatistic;
}