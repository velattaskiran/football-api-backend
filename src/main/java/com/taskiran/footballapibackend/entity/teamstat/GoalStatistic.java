package com.taskiran.footballapibackend.entity.teamstat;

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

    @Column(name = "over_0.5_for_goals")
    private Long over0_5ForGoals;

    @Column(name = "over_1.5_for_goals")
    private Long over1_5ForGoals;

    @Column(name = "over_2.5_for_goals")
    private Long over2_5ForGoals;

    @Column(name = "over_3.5_for_goals")
    private Long over3_5ForGoals;

    @Column(name = "over_4.5_for_goals")
    private Long over4_5ForGoals;

    @Column(name = "under_0.5_for_goals")
    private Long under0_5ForGoals;

    @Column(name = "under_1.5_for_goals")
    private Long under1_5ForGoals;

    @Column(name = "under_2.5_for_goals")
    private Long under2_5ForGoals;

    @Column(name = "under_3.5_for_goals")
    private Long under3_5ForGoals;

    @Column(name = "under_4.5_for_goals")
    private Long under4_5ForGoals;

    @Column(name = "over_0.5_against_goals")
    private Long over0_5AgGoals;

    @Column(name = "over_1.5_against_goals")
    private Long over1_5AgGoals;

    @Column(name = "over_2.5_against_goals")
    private Long over2_5AgGoals;

    @Column(name = "over_3.5_against_goals")
    private Long over3_5AgGoals;

    @Column(name = "over_4.5_against_goals")
    private Long over4_5AgGoals;

    @Column(name = "under_0.5_against_goals")
    private Long under0_5AgGoals;

    @Column(name = "under_1.5_against_goals")
    private Long under1_5AgGoals;

    @Column(name = "under_2.5_against_goals")
    private Long under2_5AgGoals;

    @Column(name = "under_3.5_against_goals")
    private Long under3_5AgGoals;

    @Column(name = "under_4.5_against_goals")
    private Long under4_5AgGoals;

    @Column(name = "total_for_goals_0_15_min")
    private Long totalForGoals0to15Min;

    @Column(name = "total_for_goals_16_30_min")
    private Long totalForGoals16to30Min;

    @Column(name = "total_for_goals_31_45_min")
    private Long totalForGoals31to45Min;

    @Column(name = "total_for_goals_46_60_min")
    private Long totalForGoals46to60Min;

    @Column(name = "total_for_goals_61_75_min")
    private Long totalForGoals61to75Min;

    @Column(name = "total_for_goals_76_90_min")
    private Long totalForGoals76to90Min;

    @Column(name = "total_for_goals_91_105_min")
    private Long totalForGoals91to105Min;

    @Column(name = "total_for_goals_106_120_min")
    private Long totalForGoals106to120Min;

    @Column(name = "avg_for_goals_0_15_min")
    private String avgForGoals0to15Min;

    @Column(name = "avg_for_goals_16_30_min")
    private String avgForGoals16to30Min;

    @Column(name = "avg_for_goals_31_45_min")
    private String avgForGoals31to45Min;

    @Column(name = "avg_for_goals_46_60_min")
    private String avgForGoals46to60Min;

    @Column(name = "avg_for_goals_61_75_min")
    private String avgForGoals61to75Min;

    @Column(name = "avg_for_goals_76_90_min")
    private String avgForGoals76to90Min;

    @Column(name = "avg_for_goals_91_105_min")
    private String avgForGoals91to105Min;

    @Column(name = "avg_for_goals_106_120_min")
    private String avgForGoals106to120Min;

    @Column(name = "total_against_goals_0_15_min")
    private Long totalAgGoals0to15Min;

    @Column(name = "total_against_goals_16_30_min")
    private Long totalAgGoals16to30Min;

    @Column(name = "total_against_goals_31_45_min")
    private Long totalAgGoals31to45Min;

    @Column(name = "total_against_goals_46_60_min")
    private Long totalAgGoals46to60Min;

    @Column(name = "total_against_goals_61_75_min")
    private Long totalAgGoals61to75Min;

    @Column(name = "total_against_goals_76_90_min")
    private Long totalAgGoals76to90Min;

    @Column(name = "total_against_goals_91_105_min")
    private Long totalAgGoals91to105Min;

    @Column(name = "total_against_goals_106_120_min")
    private Long totalAgGoals106to120Min;

    @Column(name = "avg_against_goals_0_15_min")
    private String avgAgGoals0to15Min;

    @Column(name = "avg_against_goals_16_30_min")
    private String avgAgGoals16to30Min;

    @Column(name = "avg_against_goals_31_45_min")
    private String avgAgGoals31to45Min;

    @Column(name = "avg_against_goals_46_60_min")
    private String avgAgGoals46to60Min;

    @Column(name = "avg_against_goals_61_75_min")
    private String avgAgGoals61to75Min;

    @Column(name = "avg_against_goals_76_90_min")
    private String avgAgGoals76to90Min;

    @Column(name = "avg_against_goals_91_105_min")
    private String avgAgGoals91to105Min;

    @Column(name = "avg_against_goals_106_120_min")
    private String avgAgGoals106to120Min;

    // TeamStatistic ile ilişki
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "team_statistic_id", referencedColumnName = "statistic_id")
    private TeamStatistic teamStatistic;
}