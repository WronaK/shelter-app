package pl.dog.dogback.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.dog.dogback.dto.SimplePetDto;
import pl.dog.dogback.dto.TermDto;
import pl.dog.dogback.entity.Term;
import pl.dog.dogback.repository.CrudUserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final CrudUserRepository userRepository;

    public List<TermDto> getMyTerms(Long userId) {
        List<Term> terms = userRepository.findById(userId)
                .orElseThrow(IllegalArgumentException::new)
                .getTerms()
                .stream()
                .toList();

        return terms
                .stream()
                .map(term -> TermDto.builder()
                        .date(term.getDate())
                        .time(term.getTime())
                        .pet(SimplePetDto.builder()
                                .image(term.getPets().getPhoto().getImage())
                                .name(term.getPets().getName())
                                .petId(term.getPets().getId())
                                .build())
                        .build())
                .collect(Collectors.toList());
    }

}
