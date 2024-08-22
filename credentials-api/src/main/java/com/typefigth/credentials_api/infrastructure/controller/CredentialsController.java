package com.typefigth.credentials_api.infrastructure.controller;


import com.typefigth.credentials_api.application.dtos.SignInDto;
import com.typefigth.credentials_api.application.dtos.SignUpDto;
import com.typefigth.credentials_api.application.dtos.CredentialsDto;
import com.typefigth.credentials_api.application.service.CredentialsService;
import com.typefigth.credentials_api.domain.models.Payload;
import com.typefigth.credentials_api.domain.models.Token;
import com.typefigth.credentials_api.domain.models.User;
import com.typefigth.credentials_api.domain.models.enun.Status;
import com.typefigth.credentials_api.infrastructure.adapters.mapper.UserMapper;
import com.typefigth.credentials_api.infrastructure.exceptions.BadCredentialsException;
import com.typefigth.credentials_api.infrastructure.repository.JpaCredentialsRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class CredentialsController {

    private final CredentialsService credentialsService;
    private final JpaCredentialsRepository jpaCredentialsRepository;
    private final UserMapper userMapper;

    private static final String BAD_CREDENTIALS = "email or password is incorrect";

    public CredentialsController(CredentialsService credentialsService, JpaCredentialsRepository jpaCredentialsRepository, UserMapper userMapper) {
        this.credentialsService = credentialsService;
        this.jpaCredentialsRepository = jpaCredentialsRepository;
        this.userMapper = userMapper;
    }

    @Transactional()
    @PostMapping("/signin")
    public ResponseEntity<CredentialsDto> signIn(@Valid @RequestBody SignInDto body) {
        User user = this.credentialsService.SignIn(body.getEmail(), body.getPassword()).orElseThrow(() -> new BadCredentialsException(BAD_CREDENTIALS));


        if (user.getStatus().equals(Status.INACTIVE)) {
            throw new BadCredentialsException("user is inactive");
        }

        Payload payload = new Payload();
        payload.setUid(user.getUid());
        Token token = new Token();

        token.setToken(this.credentialsService.generateToken(payload).get("token"));

        CredentialsDto credentialsDto = userMapper.fromUser(user, token);

        return ResponseEntity.status(HttpStatus.OK).body(credentialsDto);
    }

    @Transactional()
    @PostMapping("/signup")
    public ResponseEntity<CredentialsDto> signUp(@Valid @RequestBody SignUpDto body) {

        if (this.jpaCredentialsRepository.existsByEmail(body.getEmail())) {
            throw new BadCredentialsException("email already registered");
        }

        if (this.jpaCredentialsRepository.existsByNickname(body.getNickname())) {
            throw new BadCredentialsException("nickname already registered");
        }

        User user = new User();
        Payload payload = new Payload();
        Token token = new Token();

        user.setNickname(body.getNickname());
        user.setPassword(body.getPassword());
        user.setEmail(body.getEmail());
        user.setName(body.getName());

        User newUser = this.credentialsService.SignUp(user);

        payload.setUid(newUser.getUid());
        token.setToken(credentialsService.generateToken(payload).get("token"));

        return ResponseEntity.status(HttpStatus.OK).body(userMapper.fromUser(newUser, token));
    }

    @Transactional()
    @PostMapping("/validate")
    public ResponseEntity<Token> validate(HttpServletRequest request) {


        String token = request.getHeader("Authorization");
        if (token == null) {
            return ResponseEntity.badRequest().build();
        }

        System.out.println(token);
//        Token tokenDto = credentialsService.validate(token);
        return ResponseEntity.ok(null);
    }


}