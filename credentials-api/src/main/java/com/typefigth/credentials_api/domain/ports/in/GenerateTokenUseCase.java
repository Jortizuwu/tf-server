package com.typefigth.credentials_api.domain.ports.in;

import com.typefigth.credentials_api.domain.models.Payload;

import java.util.Map;

public interface GenerateTokenUseCase {

    Map<String, String> generateToken(Payload payload);
}
