package ru.ea_dm.models;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "discount")
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "discount_id")
    private Long discountId;

    @Column(name = "title")
    @NotEmpty(message = "Называние не может быть пустым")
    private String title;

    @Column(name = "description")
    @NotEmpty(message = "Описание не может быть пустым")
    private String description;

    @Column(name = "start_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @Column(name = "end_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Future(message = "Дата окончания акции должны быть в будущем")
    private LocalDate endDate;

    @Transient
    private Long daysLeft;

}
