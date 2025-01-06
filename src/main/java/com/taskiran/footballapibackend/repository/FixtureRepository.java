package com.taskiran.footballapibackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskiran.footballapibackend.entity.Fixture;

public interface FixtureRepository extends JpaRepository<Fixture, Long> {
    
    boolean existsByFixtureId(Long fixtureId);

    Fixture findByFixtureId(Long fixtureId);

}
