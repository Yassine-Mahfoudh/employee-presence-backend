package com.example.myapp.persistence.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;

@Data
@Entity
@Table
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Demande implements Serializable {

    @Id
    @SequenceGenerator(
            name = "Demande_sequence",
            sequenceName ="Demande_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "Demande_sequence"
    )
    private Long id;
    @Column(name = "Nom_demande")
    private String nom;
    @Column(name = "Motif_demande")
    private String motif;

    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "Date_creation")
    private Timestamp datecreation;

    @UpdateTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "Date_update")
    private Timestamp dateupdate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "Date_debut")
    private LocalDate datedebut;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "Date_fin")
    private LocalDate datefin;

    //    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToOne
    @JoinColumn(name = "etat_id", insertable = false, updatable = false)
    private EtatDemande etatDemande;

    @ManyToOne
    @JoinColumn(name = "type_id", insertable = false, updatable = false)
    private TypeDemande typeDemande;

    @ManyToOne
    @JoinColumn(name = "emp_id", insertable = false, updatable = false)
    private Employee employee;



    public Demande(String nom, String motif, LocalDate datedebut, LocalDate datefin) {
        this.nom = nom;
        this.motif = motif;
        this.datedebut = datedebut;
        this.datefin = datefin;
    }
}
