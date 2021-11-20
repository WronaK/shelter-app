package pl.dog.dogback.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import pl.dog.dogback.dto.PetDTO;
import pl.dog.dogback.dto.ShelterDTO;
import pl.dog.dogback.dto.SimplePetDto;
import pl.dog.dogback.entity.Pet;
import pl.dog.dogback.exceotions.NotFoundException;
import pl.dog.dogback.repository.CrudPetsRepository;
import pl.dog.dogback.service.usecase.PetUseCase;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<PetDTO> getFromML(Long id) {
        return getRequestFromML(id).stream()
                .map(this::getPetById)
                .collect(Collectors.toList());
    }

    @SneakyThrows
    private List<Long> getRequestFromML(Long id){
        URI uri = new URI("localhost:8081/" + id.toString());

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        String body = response.body();
        body = body.replaceAll("\\[", "");
        body = body.replaceAll("\\]", "");
        return Arrays.stream(body.split(","))
                .map(Long::parseLong)
                .collect(Collectors.toList());

    }
}
