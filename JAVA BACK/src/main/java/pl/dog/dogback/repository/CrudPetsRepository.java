package pl.dog.dogback.repository;

import org.springframework.data.repository.CrudRepository;
import pl.dog.dogback.entity.Pet;

public interface CrudPetsRepository extends CrudRepository<Pet, Long> {
}
