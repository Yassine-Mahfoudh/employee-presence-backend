package com.example.myapp.presentation.config;

import com.example.myapp.persistence.model.Employee;
import com.example.myapp.persistence.model.Event;
import com.example.myapp.persistence.repository.EventRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

@Configuration
public class EventConfig {
    @Bean
    CommandLineRunner eventconfig(
            EventRepository eventRepository){
        return args -> {


            int[] intArray = new int[]{1,3};


            Event event1 = new Event("Presentielle", "2022-05-15", "2022-05-18", "ali", "2022-05-14", "2022-05-28",new int[]{1,2});

           // event1.setDaysOfWeek(intArray);
            eventRepository.saveAll(
                  //  List.of(event1,event2,event3)
                    List.of(event1)

            );
        };
    }
}
