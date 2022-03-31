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
import java.util.ArrayList;
import java.util.Collection;

import static javax.persistence.FetchType.EAGER;

@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Fonctionalite implements Serializable {
    @SequenceGenerator(
            name = "fonctionalite_sequence",
            sequenceName ="fonctionalite_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "fonctionalite_sequence"
    )
    @Id
    @Column(name="ID_FONCTIONALITE", unique = true, nullable = false)
    private Long id;
    @Column(name = "Nom", nullable = false)
    private String nom;
    @Column(name = "Designation")
    private String designation;

    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "Date_creation")
    private Timestamp datecreation;

    @UpdateTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "Date_update")
    private Timestamp dateupdate;

    @ManyToMany(fetch = EAGER)
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<Profil> profils = new ArrayList<>();

    public Fonctionalite(String nom, String designation) {
        this.nom = nom;
        this.designation = designation;
    }

}
