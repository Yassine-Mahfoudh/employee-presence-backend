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
    /*@Column(name = "rrule")
    private RRule rrule;
*/

    public Event(String title, String start, String end) {
        this.title = title;
        this.start = start;
        this.end = end;
    }

}
