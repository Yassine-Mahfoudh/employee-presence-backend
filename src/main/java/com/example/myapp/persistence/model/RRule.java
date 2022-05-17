//package com.example.myapp.persistence.model;
//
//import com.fasterxml.jackson.annotation.JsonFormat;
//
//public class RRule {
//    private String freq;
//    private String[] byweekday;
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
//    private String dtstart;
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
//    private String until;
//
//    public RRule(String freq, String[] byweekday, String dtstart, String until) {
//        this.freq = freq;
//        this.byweekday = byweekday;
//        this.dtstart = dtstart;
//        this.until = until;
//    }
//}
