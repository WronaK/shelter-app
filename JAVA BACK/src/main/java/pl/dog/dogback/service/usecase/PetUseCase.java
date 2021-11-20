package pl.dog.dogback.service.usecase;

import pl.dog.dogback.dto.PetDTO;
import pl.dog.dogback.dto.SimplePetDto;

import java.util.List;

public interface PetUseCase {

    PetDTO getPetById(Long id);
    byte[] getPhotoById(Long id);
    List<PetDTO> getFromML(Long id);
}
