package pl.dog.dogback.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShelterDTO {

    private String name;
    private String street;
    private String city;
}
