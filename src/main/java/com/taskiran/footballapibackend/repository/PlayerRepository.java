package com.taskiran.footballapibackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskiran.footballapibackend.entity.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    boolean existsByName(String name);  // Oyuncu adını kontrol eden sorgu

}
