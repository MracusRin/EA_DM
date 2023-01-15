package ru.ea_dm.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ea_dm.models.User;
import ru.ea_dm.models.enums.Role;
import ru.ea_dm.repositories.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Optional<User> findByUsername(String name) {
        return userRepository.findByUsername(name);
    }

    @Transactional
    public void register(User user) {
        log.info("Saving new user: {}", user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    public void deleteById(Long id) {
        log.info("User with id {} DELETED", id);
        userRepository.deleteById(id);
    }

    public User findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        // TODO: Возвращать ошибку UserNotFound
        return user.orElse(null);
    }

    @Transactional
    public void update(User updatedUser, Long id) {
        log.info("User {} UPDATED", updatedUser.getUsername());
        User user = userRepository.findById(id).get();
        updatedUser.setUserId(id);
        updatedUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        updatedUser.setCreatedAt(user.getCreatedAt());
        userRepository.save(updatedUser);
    }
}
