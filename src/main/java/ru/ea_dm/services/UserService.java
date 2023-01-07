package ru.ea_dm.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ea_dm.models.User;
import ru.ea_dm.models.enums.Role;
import ru.ea_dm.repositories.UserRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> findByUsername(String name) {
        return userRepository.findByUsername(name);
    }

    @Transactional
    public void register(User user) {
        user.setCreated_at(LocalDateTime.now());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(Role.ROLE_USER);
        // TODO: добавить активацию пользователя
        user.setActive(true);
        log.info("Saving new user: {}", user.getUsername());
        userRepository.save(user);
    }
}
