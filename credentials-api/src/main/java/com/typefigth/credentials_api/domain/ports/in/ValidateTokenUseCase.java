package com.typefigth.credentials_api.domain.ports.in;

import com.typefigth.credentials_api.domain.models.Payload;
import com.typefigth.credentials_api.domain.models.Token;

import java.util.Map;

public interface ValidateTokenUseCase {

    Token validate(String token);
}
