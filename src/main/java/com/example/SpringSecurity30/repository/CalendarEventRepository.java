package com.example.SpringSecurity30.repository;

import com.example.SpringSecurity30.model.CalendarEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CalendarEventRepository extends JpaRepository<CalendarEvent, Integer> {

    Optional<CalendarEvent> findById(Integer id);

    Optional<CalendarEvent> findByDate(LocalDate date);

    List<CalendarEvent> findByDateBetweenOrderByDate(LocalDate startDate, LocalDate endDate);

}
