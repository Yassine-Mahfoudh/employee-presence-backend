package com.example.myapp.presentation.config;

import com.example.myapp.persistence.model.Projet;
import com.example.myapp.persistence.repository.ProjetRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;
@Configuration
public class ProjetConfig {
    @Bean
   CommandLineRunner commandLineRunner4( ProjetRepository repository){
        return args -> {
            Projet projet1 = new Projet (
                    "devops",
                    1,
                    "application web",
                    LocalDate.of(2022,3,2),
                    LocalDate.of(2022,7,28)

            );
            Projet projet2 = new Projet (
                    "mobdesign",
                    2,
                    "application mobile",
                    LocalDate.of(2022,4,21),
                    LocalDate.of(2022,8,20)

            );
            repository.saveAll(
                    List.of(projet1,projet2)
            );
        };
    }
}
