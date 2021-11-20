package pl.dog.dogback.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SimplePetDto {
    private Long petId;
    private String name;
    private byte[] image;
}
