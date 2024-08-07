package com.typefigth.match.infrastructure.repository;

import com.typefigth.match.infrastructure.entities.match.MatchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaMatchRepository extends JpaRepository<MatchEntity, String> {

}
