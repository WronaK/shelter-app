package pl.dog.dogback.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.dog.dogback.entity.*;
import pl.dog.dogback.exceotions.NotFoundException;
import pl.dog.dogback.repository.CrudPetsRepository;
import pl.dog.dogback.repository.CrudTermRepository;
import pl.dog.dogback.repository.CrudUserRepository;
import pl.dog.dogback.service.usecase.CalendarUseCase;

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
    private final CrudUserRepository userRepository;

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
    public void registerByDataTime(LocalDateTime dateTime, Long petId, Long userId) {
        List<Integer> freeHours = getHoursByDate(dateTime.toLocalDate(), petId);

        if(freeHours.contains(dateTime.getHour())){
            throw new IllegalArgumentException("Date is taken");
        }

        Pet pet = petsRepository.findById(petId)
                .orElseThrow(() -> new NotFoundException("Not found pet"));

        UserProfile userProfile = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Not found user"));

        Term term = new Term();
        term.setDate(dateTime.toLocalDate());
        term.setTime(dateTime.toLocalTime());
        term.setPets(pet);

        userProfile.getTerms().add(term);

        termRepository.save(term);
        userRepository.save(userProfile);
    }
}
