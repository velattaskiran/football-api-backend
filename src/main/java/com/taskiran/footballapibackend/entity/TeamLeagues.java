package com.taskiran.footballapibackend.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "team_leagues")
@IdClass(TeamLeaguesId.class)  // Composite Key sınıfını burada belirtiyoruz
public class TeamLeagues {

    @Id
    @ManyToOne
    @JoinColumn(name = "team_id", referencedColumnName = "team_id", foreignKey = @ForeignKey(name = "fk_team"))
    private Team team;
    
    @Id
    @ManyToOne
    @JoinColumn(name = "league_id", referencedColumnName = "league_id", foreignKey = @ForeignKey(name = "fk_league"))
    private League league;
}
