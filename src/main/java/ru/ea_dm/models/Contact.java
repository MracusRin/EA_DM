package ru.ea_dm.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Data
@Table(name = "contact")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id")
    private int contactId;

    @Column(name = "name", length = 50, nullable = false)
    @NotEmpty(message = "Название не может быть пустым")
    @Size(min = 3, max = 50, message = "Название должно содержать от 3 до 50 символов")
    private String name;

    @Column(name = "link", unique = true)
    private String link;

    @Column(name = "active")
    private boolean active;

}
