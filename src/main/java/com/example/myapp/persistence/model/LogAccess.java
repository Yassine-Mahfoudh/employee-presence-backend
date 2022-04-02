package com.example.myapp.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name="log_access")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LogAccess {

    @SequenceGenerator(
            name = "logAccess_sequence",
            sequenceName ="logAccess_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "logAccess_sequence"
    )
    @Id
    @Column(name = "ID_LOG_ACCESS", unique = true, nullable = false)
    private Long idLogAccess;

    @Column(name = "USERNAME", nullable = false, length = 255)
    private String username;

    @Column(name = "CODE_ACCESS", nullable = false, length = 50)
    private String codeAccess;

    @Column(name = "DATE_AUTH")
    private Timestamp dateAuth;


}