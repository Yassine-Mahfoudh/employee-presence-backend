package com.example.myapp.persistence.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class RRule {

    @Id
    @Column(name="rrule_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String freq;
    private String byweekday;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String dtstart;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String until;
}
