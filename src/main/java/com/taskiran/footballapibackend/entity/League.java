package com.taskiran.footballapibackend.entity;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "league_name", nullable = false)
    private String name;

    @Column(name = "country")
    private String country;
    
    @Column(name = "icon_path")
    private String iconPath;
}
