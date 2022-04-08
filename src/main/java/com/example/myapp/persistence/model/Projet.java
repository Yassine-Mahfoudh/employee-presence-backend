package com.example.myapp.persistence.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Set;


@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Projet {
    @SequenceGenerator(
            name = "projet_sequence",
            sequenceName ="projet_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "projet_sequence"
    )
    @Id
    @Column(name="ID_PROJET", unique = true, nullable = false)
    private Long id;
    @Column(name = "NOM")
    private String nom;
    @Column(name = "PRIORITE")
    private int priorite;
    @Column(name = "DESCRIPTION")
    private String description;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "DATE_DEBUT")
    private LocalDate datedebut;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "DATE_FIN")
    private LocalDate datefin;

    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "Date_creation")
    private Timestamp datecreation;

    @UpdateTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "Date_update")
    private Timestamp dateupdate;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PROJET")
    private Set<Employee> employees;

    public Projet(String nom, int priorite, String description, LocalDate datedebut, LocalDate datefin) {
        this.nom = nom;
        this.priorite = priorite;
        this.description = description;
        this.datedebut = datedebut;
        this.datefin = datefin;

    }
}
