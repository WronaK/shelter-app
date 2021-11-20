package pl.dog.dogback.repository;

import org.springframework.data.repository.CrudRepository;
import pl.dog.dogback.entity.Pet;
import pl.dog.dogback.entity.Term;

import java.time.LocalDate;
import java.util.List;

public interface CrudTermRepository extends CrudRepository<Term, Long> {

    List<Term> findAllByDateEqualsAndPetsId(LocalDate date, Long petId);
}
