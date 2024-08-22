package com.typefigth.credentials_api.domain.ports.in;

import com.typefigth.credentials_api.domain.models.User;

public interface SignUpUseCase {

    User SignUp(User user);
}
