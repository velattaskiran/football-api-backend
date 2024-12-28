package com.taskiran.footballapibackend.entity.teamstat;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "minute_statistics")
public class MinuteStatistic {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "minute_range")
    private String minuteRange; // Örneğin "0-15", "16-30" gibi.

    @Column(name = "total_goals")
    private Long totalGoals;

    @Column(name = "percentage")
    private String percentage; // Yüzdelik değeri.

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private UnderOverType type; // "FOR" veya "AGAINST"
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "goal_statistic_id")
    private GoalStatistic goalStatistic;
}