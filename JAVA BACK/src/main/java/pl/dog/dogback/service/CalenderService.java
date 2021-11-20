package pl.dog.dogback.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.dog.dogback.entity.OpenHours;
import pl.dog.dogback.entity.Pet;
import pl.dog.dogback.entity.Shelter;
import pl.dog.dogback.entity.Term;
import pl.dog.dogback.exceotions.NotFoundException;
import pl.dog.dogback.repository.CrudPetsRepository;
import pl.dog.dogback.repository.CrudTermRepository;
import pl.dog.dogback.service.usecase.CalendarUseCase;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class CalenderService implements CalendarUseCase {

    private final CrudPetsRepository petsRepository;
    private final CrudTermRepository termRepository;

    @Override
    public List<Integer> getHoursByDate(LocalDate date, Long petId) {

        Shelter shelter = petsRepository.findById(petId)
                .orElseThrow(() -> new NotFoundException("Not found pet"))
                .getShelter();

        List<Integer> reservation = termRepository.findAllByDateEqualsAndPetsId(date, petId)
                .stream()
                .map(term -> term.getTime().getHour())
                .collect(Collectors.toList());

        return shelter.getOpenHours()
                .stream()
                .filter(term -> term.getDay() == date.getDayOfWeek())
                .map(this :: mapToHours)
                .flatMap(Collection::stream)
                .filter(hour -> !reservation.contains(hour))
                .collect(Collectors.toList());

    }

    private List<Integer> mapToHours(OpenHours openHours){
        return IntStream.rangeClosed(openHours.getFrom(), openHours.getTo())
                .boxed()
                .collect(Collectors.toList());
    }

    @Override
    public void registerByDataTime(LocalDateTime dateTime, Long petId) {
        List<Integer> freeHours = getHoursByDate(dateTime.toLocalDate(), petId);

        if(freeHours.contains(dateTime.getHour())){
            throw new IllegalArgumentException("Date is taken");
        }

        Pet pet = petsRepository.findById(petId)
                .orElseThrow(() -> new NotFoundException("Not found pet"));

//        Term term = Term.builder()
//                .date(dateTime)
//                .pets(pet)
//                .build();
//
//        termRepository.save(term);
    }
}
