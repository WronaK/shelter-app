package pl.dog.dogback.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.DayOfWeek;

@Entity
@Data
public class OpenHours {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private DayOfWeek day;

    @Column(name="valueFrom")
    private int from;

    @Column(name="valueTo")
    private int to;

}
