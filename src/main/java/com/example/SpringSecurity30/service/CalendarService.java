package com.example.SpringSecurity30.service;

import com.example.SpringSecurity30.model.CalendarDay;
import com.example.SpringSecurity30.model.CalendarEvent;
import com.example.SpringSecurity30.repository.CalendarEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CalendarService {

    @Autowired
    private CalendarEventRepository eventRepository;

    public List<CalendarDay> getCalendarMonth(int year, int month) {
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());

        List<CalendarDay> calendarMonth = new ArrayList<>();

        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            Optional<CalendarEvent> eventOptional = eventRepository.findByDate(date);
            String description = eventOptional.map(CalendarEvent::getDescription).orElse("");

            calendarMonth.add(new CalendarDay(date, description));
        }

        return calendarMonth;
    }

    public Optional<CalendarEvent> getEventsByDate(LocalDate date) {
        return eventRepository.findByDate(date);
    }

    public void addOrUpdateEvent(LocalDate date, String description) {
        Optional<CalendarEvent> optionalEvent = eventRepository.findByDate(date);

        if (optionalEvent.isPresent()) {
            CalendarEvent event = optionalEvent.get();
            event.setDescription(description);
            eventRepository.save(event);
        } else {
            CalendarEvent event = new CalendarEvent();
            event.setDate(date);
            event.setDescription(description);
            eventRepository.save(event);
        }
    }

    public void deleteEvent(Integer id) {
        eventRepository.deleteById(id);
    }
}
