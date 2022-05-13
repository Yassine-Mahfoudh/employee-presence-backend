package com.example.myapp.persistence.repository;

import com.example.myapp.persistence.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event,Long> {

    @Query("select d from Event d where d.id=:id ")
    public Event findEventById(@Param("id") Long id);

    @Query("select d from Event d where d.title=:title ")
    public Event findEventByTitle(@Param("title") String title);
}
