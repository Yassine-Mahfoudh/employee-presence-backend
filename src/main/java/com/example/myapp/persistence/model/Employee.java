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
    @Column(name = "Employee_lastname")
    private String lastname;
    @Column(name = "Employee_firstname")
    private String firstname;
    @Column(name = "Employee_role")
    private String role;
    @Column(name = "Employee_status")
    private Boolean status;
    @Column(name = "Birth_date")
    private LocalDate birthdate;
    @Column(name = "Employee_address")
    private String address;
    @Column(name = "Employee_phone")
    private String phonenumber;



    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "Creation_date")
    private Timestamp creationdate;

    @UpdateTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "Update_date")
    private Timestamp updatedate;


    //@OneToOne(mappedBy = "employee")
    //private Utilisateur utilisateur;


    @JoinColumn(name = "project_id", insertable = false, updatable = false)
    private String project;

    @JoinColumn(name = "salle_id", insertable = false, updatable = false)
    private int salle;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "emp_id")
    private Set<Demande> demandes;

    public Employee(String lastname, String firstname, String role, Boolean status, LocalDate birthdate, String address, String phonenumber, String project, int salle) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.role = role;
        this.status = status;
        this.birthdate = birthdate;
        this.address = address;
        this.phonenumber = phonenumber;
        this.project = project;
        this.salle = salle;
    }
}
