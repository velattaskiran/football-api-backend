package com.taskiran.footballapibackend.entity.teamstat;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "goal_statistics")
public class GoalStatistic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Toplam goller için alanlar
    @Column(name = "goals_for_home")
    private Long goalsForHome;

    @Column(name = "goals_for_away")
    private Long goalsForAway;

    @Column(name = "goals_for_total")
    private Long goalsForTotal;

    @Column(name = "goals_against_home")
    private Long goalsAgainstHome;

    @Column(name = "goals_against_away")
    private Long goalsAgainstAway;

    @Column(name = "goals_against_total")
    private Long goalsAgainstTotal;

    @Column(name = "average_goals_for_home")
    private String averageGoalsForHome;

    @Column(name = "average_goals_for_away")
    private String averageGoalsForAway;

    @Column(name = "average_goals_for_total")
    private String averageGoalsForTotal;

    @Column(name = "average_goals_against_home")
    private String averageGoalsAgainstHome;

    @Column(name = "average_goals_against_away")
    private String averageGoalsAgainstAway;

    @Column(name = "average_goals_against_total")
    private String averageGoalsAgainstTotal;
    
    @OneToMany(mappedBy = "goalStatistic", cascade = CascadeType.ALL)
    private List<MinuteStatistic> minuteStatistics; 

    // UnderOver İstatistikleri
    @OneToMany(mappedBy = "goalStatistic", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UnderOverStatistic> underOverStatistics;

    // TeamStatistic ile ilişki
    @OneToOne
    @JoinColumn(name = "team_statistic_id", referencedColumnName = "statistic_id")
    private TeamStatistic teamStatistic;
}