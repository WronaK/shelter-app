package pl.dog.dogback.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Pets {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private Date birthday;
    private Date shelterDate;
    private String description;

    @OneToOne
    private Photo photo;

    @ManyToOne
    private Shelter shelter;


}
