package ru.ea_dm.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "contact")
public class Contact {
    // TODO: Добавить валидацию модели Контактов
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id")
    private int contactId;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "vk")
    private String vk;

    @Column(name = "telegram")
    private String telegram;

    @Column(name = "instagram")
    private String instagram;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;
}
