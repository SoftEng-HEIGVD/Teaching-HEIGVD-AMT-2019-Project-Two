package ch.heig.amt.pokemon.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter

@Entity
public class TrainerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer trainerId;

    private String name;

    private String surname;

    private String gender;

    private Integer age;

    private Integer numberOfBadges;
}
