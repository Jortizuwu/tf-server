package com.typefigth.user.infrastructure.adapters.user.mapper;

import com.typefigth.user.domain.models.user.User;
import com.typefigth.user.infrastructure.entities.user.UserEntity;

public interface UserMapper {

    UserEntity toEntity(User user);

    User fromEntity(UserEntity entity);

}
