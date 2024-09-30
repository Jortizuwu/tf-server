package com.typefigth.credentials_api.infrastructure.adapters.mapper;


import com.typefigth.credentials_api.application.dtos.CredentialsDto;
import com.typefigth.credentials_api.domain.models.Token;
import com.typefigth.credentials_api.domain.models.User;
import com.typefigth.credentials_api.infrastructure.entity.UserEntity;

public class UserMapperAdapter implements UserMapper {

    public UserEntity toEntity(User user) {
        return new UserEntity.UserEntityBuilder()
                .setUid(user.getUid())
                .setNickname(user.getNickname())
                .setPassword(user.getPassword())
                .setEmail(user.getEmail())
                .setCreatedAt(user.getCreatedAt())
                .setUpdatedAt(user.getUpdatedAt())
                .setStatus(user.getStatus())
                .build();

    }

    public User fromEntity(UserEntity entity) {
        return new User.UserBuilder()
                .setUid(entity.getUid())
                .setStatus(entity.getStatus())
                .setNickname(entity.getNickname())
                .setPassword(entity.getPassword())
                .setEmail(entity.getEmail())
                .setCreatedAt(entity.getCreatedAt())
                .setUpdatedAt(entity.getUpdatedAt())
                .build();
    }

    @Override
    public CredentialsDto fromUser(User user, Token token) {
        return new CredentialsDto.UserDtoBuilder()
                .setUid(user.getUid())
                .setNickname(user.getNickname())
                .setEmail(user.getEmail())
                .setToken(token)
                .setStatus(user.getStatus())
                .setCreatedAt(user.getCreatedAt())
                .setUpdatedAt(user.getUpdatedAt())
                .build();
    }

}
