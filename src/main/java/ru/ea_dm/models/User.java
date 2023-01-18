package ru.ea_dm.models;

import lombok.Data;
import ru.ea_dm.models.enums.Role;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "username", unique = true)
    @NotEmpty(message = "Имя пользователя не может быть пустым")
    @Size(min = 3, max = 30, message = "Имя пользователя должно содержать от 3 до 30 символов")
    private String username;

    @Column(name = "email")
    @Email(message = "Email введен не корректно")
    private String email;

    @Column(name = "password")
    @Size(min = 3, message = "Пароль должен содержать как минимум 3 символа")
    @NotEmpty(message = "Пароль не может быть пустым")
    private String password;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "active")
    private boolean active;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>();

    @PrePersist
    private void init() {
        createdAt = LocalDateTime.now();
    }

    public boolean hasRole(Role role) {
        return this.roles.contains(role);
    }

}
