package pl.dog.dogback.service.usecase;

import pl.dog.dogback.dto.PetDTO;

public interface PetUseCase {

    PetDTO getPetById(Long id);
    byte[] getPhotoById(Long id);
}
