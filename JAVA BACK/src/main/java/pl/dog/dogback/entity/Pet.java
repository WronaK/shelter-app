package pl.dog.dogback.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String puggle;
    private LocalDate birthday;
    private LocalDate shelterDate;
    private String description;

    @OneToOne
    private Photo photo;

    @ManyToOne
    private Shelter shelter;
}
