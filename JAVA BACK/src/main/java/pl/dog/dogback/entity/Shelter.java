package pl.dog.dogback.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Shelter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String street;
    private String city;

    @OneToMany
    private Set<OpenHours> openHours;

    @OneToMany(mappedBy="shelter")
    private Set<Pet> pets;

}
