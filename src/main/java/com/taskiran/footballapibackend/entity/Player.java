package com.taskiran.footballapibackend.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "players")
public class Player {    
    @Id
    @Column(name = "player_id")
    private Long id;

    @Column(name = "player_name", nullable = false)
    private String name;

    @Column(name = "age")
    private long age;

    @Column(name = "number")
    private long number;

    @Column(name = "position")
    private String position;

    @Column(name = "team_id")
    private long teamId;

    @Column(name = "team_name")
    private String teamName;
    
    @Column(name = "photo_url")
    private String photo;
}
