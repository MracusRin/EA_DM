package ru.ea_dm.models;

import lombok.Data;
import ru.ea_dm.models.enums.Gender;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "service")
@Data
public class ServiceImpl {
    // TODO: Добавить в модель ServiceImpl необходимые поля (время выполнения и пр.)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_id")
    private Long serviceId;

    @Column(name = "title")
    @NotEmpty(message = "Название не может быть пустым")
    @Size(min = 3, message = "Название должно содержать как минимум 3 символа")
    private String title;

    @Column(name = "description")
    @NotEmpty(message = "Описание не может быть пустым")
    @Size(min = 3, message = "Описание должно содержать как минимум 3 символа")
    private String description;

    @Column(name = "price")
    @Min(value = 0, message = "Цена должна быть положительным числом")
    @NotNull(message = "Цена не может быть пустой")
    private Integer price;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "active")
    private boolean active;

    @ElementCollection(targetClass = Gender.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "gender", joinColumns = @JoinColumn(name = "service_id"))
    @Enumerated(EnumType.STRING)
    private Set<Gender> genders = new HashSet<>();

    @PrePersist
    private void init() {
        createdAt = LocalDateTime.now();
    }

    public boolean hasGender(Gender gender) {
        return this.genders.contains(gender);
    }
}
