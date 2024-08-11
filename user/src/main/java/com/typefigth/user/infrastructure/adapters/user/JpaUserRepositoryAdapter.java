package com.typefigth.user.infrastructure.adapters.user;

import com.typefigth.user.domain.models.user.User;
import com.typefigth.user.domain.models.user.enun.Status;
import com.typefigth.user.domain.ports.out.user.UserRepositoryPort;
import com.typefigth.user.infrastructure.adapters.user.mapper.UserMapper;
import com.typefigth.user.infrastructure.entities.user.UserEntity;
import com.typefigth.user.infrastructure.repository.JpaUserRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JpaUserRepositoryAdapter implements UserRepositoryPort {

    private final JpaUserRepository jpaUserRepository;

    private final UserMapper userMapper;

    public JpaUserRepositoryAdapter(JpaUserRepository jpaUserRepository, UserMapper userMapper) {
        this.jpaUserRepository = jpaUserRepository;
        this.userMapper = userMapper;
    }

    @Override
    public Optional<User> findById(String id) {
        Optional<UserEntity> optionalUserEntity = this.jpaUserRepository.findById(id);

        if (optionalUserEntity.isEmpty()) {
            return Optional.empty();
        }

        User todo = userMapper.fromEntity(optionalUserEntity.get());
        return Optional.of(todo);
    }

    @Override
    public List<User> findAll() {
        return this.jpaUserRepository.findAll().stream().map(userMapper::fromEntity).toList();
    }

    @Override
    public boolean deleteById(String id) {
        Optional<UserEntity> user = this.jpaUserRepository.findById(id);
        if (user.isPresent()) {
            user.get().setStatus(Status.INACTIVE);
            this.jpaUserRepository.save(user.get());
            return true;
        }
        return false;
    }

    @Override
    public Optional<User> updateUser(String id, User body) {
        if (this.jpaUserRepository.existsById(id)) {
            UserEntity todoEntity = this.userMapper.toEntity(body);
            UserEntity todoUpdated = this.jpaUserRepository.save(todoEntity);
            return Optional.of(this.userMapper.fromEntity(todoUpdated));
        }
        return Optional.empty();
    }

    @Override
    public User save(User user) {
        return this.userMapper.fromEntity(this.jpaUserRepository.save(userMapper.toEntity(user)));
    }
}
