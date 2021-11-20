package pl.dog.dogback.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class OpenHours {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Day day;
    private int from;
    private int to;

}
