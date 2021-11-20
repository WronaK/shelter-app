package pl.dog.dogback.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class PetDTO {

    private String name;
    private String puggle;
    private int age;
    private LocalDate shelterDate;
    private String description;
    private byte[] photo;
    private ShelterDTO shelter;
}
