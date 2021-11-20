package pl.dog.dogback.controller;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.web.bind.annotation.*;
import pl.dog.dogback.service.usecase.CalendarUseCase;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static pl.dog.dogback.ApiPath.CALENDAR;

@RestController
@RequestMapping(CALENDAR)
@RequiredArgsConstructor
public class CalendarController {

    private final CalendarUseCase calendar;

    @GetMapping
    List<Integer> getFreeHours(@RequestParam String date, @RequestParam Long dogId){
        LocalDate localDate = LocalDate.parse(date);
        return calendar.getHoursByDate(localDate, dogId);
    }

    @PostMapping
    void addNewVisit(@RequestBody AddVisitDTO addVisitDTO){
        calendar.registerByDataTime(addVisitDTO.getDataTime(), addVisitDTO.getPetId(), addVisitDTO.getUserId());
    }

    @Value
    private class AddVisitDTO {
        Long userId;
        Long petId;
        LocalDateTime dataTime;
    }
}
