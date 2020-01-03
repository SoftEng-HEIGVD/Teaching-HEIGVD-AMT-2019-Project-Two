package ch.heig.amt.pokemon.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter

@Entity
public class PokemonEntity {
    @Id
    private Integer pokeDexId;

    private String name;

    private String type;

    private String category;

    private Integer height;

    private Integer hp;
}
