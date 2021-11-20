package pl.dog.dogback.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.dog.dogback.dto.PetDTO;
import pl.dog.dogback.dto.ShelterDTO;
import pl.dog.dogback.entity.Pet;
import pl.dog.dogback.exceotions.NotFoundException;
import pl.dog.dogback.repository.CrudPetsRepository;
import pl.dog.dogback.service.usecase.PetUseCase;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class PetService implements PetUseCase {

    private final CrudPetsRepository petsRepository;

    @Override
    public PetDTO getPetById(Long id) {
        Pet pet = petsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found pet"));

        return PetDTO.builder()
                .name(pet.getName())
                .age(LocalDate.now().getYear() - pet.getBirthday().getYear())
                .shelterDate(pet.getShelterDate())
                .description(pet.getDescription())
                .shelter(ShelterDTO.builder()
                        .city(pet.getShelter().getCity())
                        .street(pet.getShelter().getStreet())
                        .name(pet.getShelter().getName())
                        .build())
                .puggle(pet.getPuggle())
                .photo(pet.getPhoto().getImage())
                .build();
    }

    @Override
    public byte[] getPhotoById(Long id) {
        return petsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found pet"))
                .getPhoto().getImage();
    }
}
