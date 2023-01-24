package ru.ea_dm.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "discount")
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "discount_id")
    private Long discountId;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

}
