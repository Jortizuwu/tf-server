package com.typefigth.credentials_api.infrastructure.adapters.mapper;


import com.typefigth.credentials_api.application.dtos.CredentialsDto;
import com.typefigth.credentials_api.domain.models.Token;
import com.typefigth.credentials_api.domain.models.User;
import com.typefigth.credentials_api.infrastructure.entity.UserEntity;

public interface UserMapper {

    UserEntity toEntity(User user);

    User fromEntity(UserEntity entity);

    CredentialsDto fromUser(User user, Token token);
}
