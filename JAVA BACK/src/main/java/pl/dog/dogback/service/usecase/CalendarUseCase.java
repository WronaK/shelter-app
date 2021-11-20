package pl.dog.dogback.service.usecase;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface CalendarUseCase {

    List<Integer> getHoursByDate(LocalDate date, Long dogId);

    void registerByDataTime(LocalDateTime dateTime, Long dogId);
}
