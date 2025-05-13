# Employee Presence – Backend

Ce dépôt contient le back-end de l'application web de planification de la présence des employés, développé lors de mon stage chez ST2i (février – juillet 2022).

##  Fonctionnalités principales
- Gestion des plannings de présence
- Authentification des utilisateurs
- Exposition d'API REST pour le front-end
- Stockage des données en base

##  Stack technique
- Java 11
- Spring Boot
- JPA / Hibernate
- Base de données (PostgreSQL)
- Maven
  
##  Structure du projet
src/main/java – logique métier

src/main/resources – configuration (application.properties)

##  Lancer le projet
```bash
git clone https://github.com/ton-profil/employee-presence-backend
cd employee-presence-backend
mvn spring-boot:run

