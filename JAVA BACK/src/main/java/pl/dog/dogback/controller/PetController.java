package pl.dog.dogback.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.dog.dogback.ApiPath;
import pl.dog.dogback.dto.PetDTO;
import pl.dog.dogback.service.usecase.PetUseCase;

import java.util.List;

@RestController
@RequestMapping(ApiPath.PET)
@RequiredArgsConstructor
public class PetController {

    private final PetUseCase petService;

    @GetMapping
    public PetDTO getPetById(@RequestParam Long id){
        return petService.getPetById(id);
    }

    @GetMapping("/image")
    public byte[] getPetImage(@RequestParam Long id){
        return petService.getPhotoById(id);
    }

    @GetMapping("/pets")
    public List<PetDTO> getPetsByLikely(@RequestParam(required = false, defaultValue = "-1") Long id){
        return petService.getFromML(id);
    }
}
