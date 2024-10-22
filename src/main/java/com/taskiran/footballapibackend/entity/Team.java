package com.taskiran.footballapibackend.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "teams")
public class Team {    
    @Id
    @Column(name = "team_id")
    private Long id;

    @Column(name = "team_name", nullable = false)
    private String name;

    @Column(name = "country")
    private String country;
    
    @Column(name = "logo_url")
    private String logo;

    @Column(name = "league_id")
    private Long leagueId;
}
