package pl.dog.dogback.exceotions;


public class NotFoundException extends IllegalArgumentException {
    String not_found_pet;

    public NotFoundException(String not_found_pet) {
        super();
        this.not_found_pet = not_found_pet;
    }
}
