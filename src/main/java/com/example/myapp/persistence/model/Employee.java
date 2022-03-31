package com.example.myapp.persistence.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Employee implements Serializable {

    @Id
    @SequenceGenerator(
            name = "employee_sequence",
            sequenceName ="employee_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "employee_sequence"
    )
    private Long id;
    @Column(name = "Nom_employee")
    private String nom;
    @Column(name = "Prenom_employee")
    private String prenom;
    @Column(name = "Role_employee")
    private String role;
    @Column(name = "Etat_employee")
    private Boolean etat;
    @Column(name = "Date_naiss")
    private LocalDate datenaiss;
    @Column(name = "Adresse_employee")
    private String adresse;

    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "Date_creation")
    private Timestamp datecreation;

    @UpdateTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "Date_update")
    private Timestamp dateupdate;


    @OneToOne(mappedBy = "employee")
    private Utilisateur utilisateur;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToOne
    @JoinColumn(name = "projet_id", insertable = false, updatable = false)
    private Projet projet;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToOne
    @JoinColumn(name = "salle_id", insertable = false, updatable = false)
    private Salle salle;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "emp_id")
    private Set<Demande> demandes;

    public Employee(String nom, String prenom, String role, Boolean etat, LocalDate datenaiss, String adresse) {
        this.nom = nom;
        this.prenom = prenom;
        this.role = role;
        this.etat = etat;
        this.datenaiss = datenaiss;
        this.adresse = adresse;
    }
}
