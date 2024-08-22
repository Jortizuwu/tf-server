package com.typefigth.credentials_api.infrastructure.adapters;


import com.typefigth.credentials_api.domain.models.Payload;
import com.typefigth.credentials_api.domain.models.Token;
import com.typefigth.credentials_api.domain.models.User;
import com.typefigth.credentials_api.domain.ports.out.CredentialsRepositoryPort;
import com.typefigth.credentials_api.infrastructure.adapters.mapper.UserMapper;
import com.typefigth.credentials_api.infrastructure.entity.UserEntity;
import com.typefigth.credentials_api.infrastructure.repository.JpaCredentialsRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwe;
import io.jsonwebtoken.Jwts;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class JpaCredentialsRepositoryAdapter implements CredentialsRepositoryPort {

    private final JpaCredentialsRepository jpaCredentialsRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final SecretKey key = Jwts.SIG.HS256.key().build();

    public JpaCredentialsRepositoryAdapter(JpaCredentialsRepository jpaCredentialsRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.jpaCredentialsRepository = jpaCredentialsRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User SignUp(User user) {
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        return this.userMapper.fromEntity(this.jpaCredentialsRepository.save(userMapper.toEntity(user)));
    }

    @Override
    public Optional<User> SignIn(String email, String password) {
        Optional<UserEntity> optionalUserEntity = this.jpaCredentialsRepository.findByEmail(email);

        if (optionalUserEntity.isEmpty()) {
            return Optional.empty();
        }

        if (!this.passwordEncoder.matches(password, optionalUserEntity.get().getPassword())) {
            return Optional.empty();
        }

        User user = userMapper.fromEntity(optionalUserEntity.get());
        return Optional.of(user);
    }

    @Override
    public Map<String, String> generateToken(Payload payload) {

        long expirationTimeMillis = 2 * 60 * 60 * 1000;
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationTimeMillis);

        String jwtToken = Jwts.builder().subject(payload.getUid()).expiration(expiryDate).signWith(key).compact();
        Map<String, String> jwtTokenGen = new HashMap<>();
        jwtTokenGen.put("token", jwtToken);
        return jwtTokenGen;
    }

    @Override
    public Token validate(String token) {
        Jwe<Claims> jws = Jwts.parser().verifyWith(key).build().parseEncryptedClaims(token);
        return new Token(jws.getPayload().getSubject(), LocalDateTime.now().plusMinutes(2));
    }
}
