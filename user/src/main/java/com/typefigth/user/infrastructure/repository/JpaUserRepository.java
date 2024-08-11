package com.typefigth.user.infrastructure.repository;

import com.typefigth.user.infrastructure.entities.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaUserRepository extends JpaRepository<UserEntity, String> {
   boolean existsByEmail(String email);
   boolean existsByNickname(String nickname);
}
