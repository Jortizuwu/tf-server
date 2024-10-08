package com.typefigth.user.infrastructure.adapters.user.mapper;

import com.typefigth.user.domain.models.user.User;
import com.typefigth.user.infrastructure.entities.user.UserEntity;

public class UserMapperAdapter implements UserMapper {

    public UserEntity toEntity(User user) {
        return new UserEntity.UserEntityBuilder()
                .setUid(user.getUid())
                .setNickname(user.getNickname())
                .setPassword(user.getPassword())
                .setEmail(user.getEmail())
                .setName(user.getName())
                .setCreatedAt(user.getCreatedAt())
                .setUpdatedAt(user.getUpdatedAt())
                .build();

    }

    public User fromEntity(UserEntity entity) {
        return new User.UserBuilder()
                .setUid(entity.getUid())
                .setNickname(entity.getNickname())
                .setPassword(entity.getPassword())
                .setEmail(entity.getEmail())
                .setName(entity.getName())
                .setCreatedAt(entity.getCreatedAt())
                .setUpdatedAt(entity.getUpdatedAt())
                .build();
    }

}
