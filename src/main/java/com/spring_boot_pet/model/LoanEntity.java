package com.spring_boot_pet.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import java.time.LocalDate;

@Entity
@Table(name = "loans")
@Setter
@Getter
@NoArgsConstructor
public class LoanEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // Дата выдачи книги читателю.
    @Column(name = "loan_date")
    private LocalDate loanDate;

    // Планируемая дата возврата.
    @Column(name = "due_date")
    private LocalDate dueDate;

    // Фактическая дата возврата.
    @Column(name = "returned_date")
    private LocalDate returnedDate;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private BookEntity book;

    @ManyToOne
    @JoinColumn(name = "reader_id")
    private ReaderEntity reader;

}