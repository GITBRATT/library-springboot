package com.spring_boot_pet.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "books")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    @NotBlank(message = "Название обязательно")
    private String title;

    @Column(name = "author", nullable = false)
    @NotBlank(message = "Автор обязателен")
    private String author;

    @Column(name = "publicationYear", nullable = false)
    @NotNull(message = "Укажите год")
    @Min(value = 4, message = "Год не может быть отрицательным")
    private Integer publicationYear;

}
