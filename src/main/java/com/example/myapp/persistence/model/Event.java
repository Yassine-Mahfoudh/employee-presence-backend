package com.example.myapp.persistence.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Event {
    @Id
    @SequenceGenerator(
            name = "event_sequence",
            sequenceName ="event_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "event_sequence"
    )
    private Long id;
    @Column(name = "event_title")
    private String title;

    @Column(name = "event_start")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String start;

    @Column(name = "event_end")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String end;


    @Column(name = "employee")
    private String employee;

    @Column(name = "startRecur")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String startRecur;

    @Column(name = "endRecur")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String endRecur;

    @Transient
    private int[] daysOfWeek;


    public Event(String title, String start, String end, String employee, String startRecur, String endRecur, int[] daysOfWeek) {
        this.title = title;
        this.start = start;
        this.end = end;
        this.employee = employee;
        this.startRecur = startRecur;
        this.endRecur = endRecur;
        this.daysOfWeek = daysOfWeek;
    }
}
