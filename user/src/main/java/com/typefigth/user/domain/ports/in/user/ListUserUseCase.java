package com.typefigth.user.domain.ports.in.user;

import com.typefigth.user.domain.models.user.User;

import java.util.List;

public interface ListUserUseCase {

    List<User> listUsers();

}
