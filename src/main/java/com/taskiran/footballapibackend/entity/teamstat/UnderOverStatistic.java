package com.taskiran.footballapibackend.entity.teamstat;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "under_over_statistics")
public class UnderOverStatistic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "over_under_value")
    private String overUnderValue; // Örneğin: "0.5", "1.5", vs.

    @Column(name = "over")
    private Long over;

    @Column(name = "under")
    private Long under;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private UnderOverType type; // "FOR" veya "AGAINST"

    // İlişki
    @ManyToOne
    @JoinColumn(name = "goal_statistic_id", referencedColumnName = "id")
    private GoalStatistic goalStatistic;
}
