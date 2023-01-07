package ru.ea_dm.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "product")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int productId;

    @Column(name = "title")
    @NotEmpty(message = "Название не может быть пустым")
    @Size(min = 3, message = "Называние должно содержать как минимум 3 символа")
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

}
