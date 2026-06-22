package com.spring_boot_pet.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "reader")
@Setter
@Getter
@NoArgsConstructor
public class ReaderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "full_name", nullable = false)
    @NotBlank(message = "Имя обязательно")
    private String fullName;

    @OneToMany(mappedBy = "reader")
    private List <LoanEntity> loans = new ArrayList<>();

}