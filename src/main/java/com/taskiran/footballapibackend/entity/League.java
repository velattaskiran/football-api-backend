package com.taskiran.footballapibackend.entity;

import java.util.Set;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "leagues")
public class League {

    @Id
    @Column(name = "league_id")
    private Long leagueId;

    @Column(name = "league_name", nullable = false)
    private String name;

    @Column(name = "country")
    private String country;
    
    @Column(name = "logo_url")
    private String logoUrl;

    @OneToMany(mappedBy = "league")
    private Set<TeamLeagues> teamLeagues;
    
    public League(Long leagueId) {
        this.leagueId = leagueId;
    }
}
