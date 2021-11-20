package pl.dog.dogback.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
public class TermDto {
    private LocalDate date;
    private LocalTime time;
    private SimplePetDto pet;
}
