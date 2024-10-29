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
@Table(name = "teams")
public class Team {    
    @Id
    @Column(name = "team_id")
    private Long teamId;

    @Column(name = "team_name", nullable = false)
    private String name;
    
    @Column(name = "logo_url")
    private String logo;

    @OneToMany(mappedBy = "team")
    private Set<TeamLeagues> teamLeagues;
}
