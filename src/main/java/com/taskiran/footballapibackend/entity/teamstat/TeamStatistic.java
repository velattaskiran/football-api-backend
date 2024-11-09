package com.taskiran.footballapibackend.entity.teamstat;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "teams_statistics")
public class TeamStatistic {
    @Id
    @Column(name = "statistic_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "team_id")
    private Long teamId;

    @Column(name = "league_id")
    private Long leagueId;

    @Column(name = "season")
    private Long season;

    @Column(name = "form")
    private String form;

    @Column(name = "clean_sheets_home")
    private Long cleanSheetsHome;

    @Column(name = "clean_sheets_away")
    private Long cleanSheetsAway;

    @Column(name = "clean_sheets_total")
    private Long cleanSheetsTotal;

    @Column(name = "failed_to_score_home")
    private Long failedToScoreHome;

    @Column(name = "failed_to_score_away")
    private Long failedToScoreAway;

    @Column(name = "failed_to_score_total")
    private Long failedToScoreTotal;

    @Column(name = "penalty_scored")
    private Long penaltyScored;

    @Column(name = "penalty_scored_percentage")
    private String penaltyScoredPercentage;
    
    @Column(name = "penalty_missed")
    private Long penaltyMissed;

    @Column(name = "penalty_missed_percentage")
    private String penaltyMissedPercentage;

    // Alt tablolarla ilişkiler
    @OneToOne(mappedBy = "teamStatistic", cascade = CascadeType.ALL)
    private FixtureStatistic fixtureStatistic;

    @OneToOne(mappedBy = "teamStatistic", cascade = CascadeType.ALL)
    private GoalStatistic goalStatistic;
    
    @OneToOne(mappedBy = "teamStatistic", cascade = CascadeType.ALL)
    private BiggestStatistic biggestStatistic;
    
    @Column(name = "yellow_card_0_to_15")
    private Long yellowCard0to15;

    @Column(name = "yellow_card_16_to_30")
    private Long yellowCard16to30;

    @Column(name = "yellow_card_31_to_45")
    private Long yellowCard31to45;

    @Column(name = "yellow_card_46_to_60")
    private Long yellowCard46to60;

    @Column(name = "yellow_card_61_to_75")
    private Long yellowCard61to75;

    @Column(name = "yellow_card_76_to_90")
    private Long yellowCard76to90;

    @Column(name = "yellow_card_91_to_105")
    private Long yellowCard91to105;

    @Column(name = "yellow_card_106_to_120")
    private Long yellowCard106to120;

    @Column(name = "percentage_yellow_card_0_to_15")
    private Long perYellowCard0to15;

    @Column(name = "percentage_yellow_card_16_to_30")
    private Long perYellowCard16to30;

    @Column(name = "percentage_yellow_card_31_to_45")
    private Long perYellowCard31to45;

    @Column(name = "percentage_yellow_card_46_to_60")
    private Long perYellowCard46to60;

    @Column(name = "percentage_yellow_card_61_to_75")
    private Long perYellowCard61to75;

    @Column(name = "percentage_yellow_card_76_to_90")
    private Long perYellowCard76to90;

    @Column(name = "percentage_yellow_card_91_to_105")
    private Long perYellowCard91to105;

    @Column(name = "percentage_yellow_card_106_to_120")
    private Long perYellowCard106to120;
    
    @Column(name = "red_card_0_to_15")
    private Long redCard0to15;

    @Column(name = "red_card_16_to_30")
    private Long redCard16to30;

    @Column(name = "red_card_31_to_45")
    private Long redCard31to45;

    @Column(name = "red_card_46_to_60")
    private Long redCard46to60;

    @Column(name = "red_card_61_to_75")
    private Long redCard61to75;

    @Column(name = "red_card_76_to_90")
    private Long redCard76to90;

    @Column(name = "red_card_91_to_105")
    private Long redCard91to105;

    @Column(name = "red_card_106_to_120")
    private Long redCard106to120;

    @Column(name = "percentage_red_card_0_to_15")
    private Long perRedCard0to15;

    @Column(name = "percentage_red_card_16_to_30")
    private Long perRedCard16to30;

    @Column(name = "percentage_red_card_31_to_45")
    private Long perRedCard31to45;

    @Column(name = "percentage_red_card_46_to_60")
    private Long perRedCard46to60;

    @Column(name = "percentage_red_card_61_to_75")
    private Long perRedCard61to75;

    @Column(name = "percentage_red_card_76_to_90")
    private Long perRedCard76to90;

    @Column(name = "percentage_red_card_91_to_105")
    private Long perRedCard91to105;

    @Column(name = "percentage_red_card_106_to_120")
    private Long perRedCard106to120;    
}