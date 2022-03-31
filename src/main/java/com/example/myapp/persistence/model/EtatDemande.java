package com.example.myapp.persistence.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table
public class EtatDemande implements Serializable {

    @Id
    @SequenceGenerator(
            name = "etatDemande_sequence",
            sequenceName ="etatDemande_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "etatDemande_sequence"
    )
    private Long id;
    @Column(name = "Statut")
    private String statut;
    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "Date_creation")
    private Timestamp datecreation;

    @OneToMany
    @JoinColumn(name = "etat_id", insertable = false, updatable = false)
    private Set<Demande> demandes;

    public EtatDemande(String statut) {
        this.statut = statut;
    }


}
