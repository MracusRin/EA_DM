package ru.ea_dm.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @Column(name = "person_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int personId;

    @Column(name = "username")
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
    private LocalDateTime created_at;

    @Column(name = "role")
    private String role;

    public Person() {}

    public Person(String username, String email, String password, LocalDateTime created_at) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.created_at = created_at;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
