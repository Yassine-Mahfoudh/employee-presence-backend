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
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import static javax.persistence.FetchType.EAGER;

@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Profil {
    @SequenceGenerator(
            name = "profil_sequence",
            sequenceName ="profil_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "profil_sequence"
    )
    @Id
    @Column(name="ID_PROFIL", unique = true, nullable = false)
    private Long id;
    @Column(name = "TYPE")
    private String type;


    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "Date_creation")
    private Timestamp datecreation;

    @UpdateTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "Date_update")
    private Timestamp dateupdate;

    @ManyToMany(fetch = EAGER)
    private Collection<Fonctionalite> fonctionalites = new ArrayList<>();

    public Profil(String type) {
        this.type = type;
    }
}
