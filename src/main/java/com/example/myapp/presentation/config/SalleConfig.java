package com.example.myapp.presentation.config;

import com.example.myapp.persistence.model.Salle;
import com.example.myapp.persistence.repository.SalleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class SalleConfig {

    @Bean
    CommandLineRunner commandLineRunner122(
            SalleRepository repository22){
        return args -> {
            Salle salle1 = new Salle(
                    "dem1",
                    1,
                    15,
                    30

            );
            Salle salle2 = new Salle(
                    "dem2",
                    2,
                    8,
                    50
            );
            repository22.saveAll(
                    List.of(salle1,salle2)
            );
        };
    }
}
