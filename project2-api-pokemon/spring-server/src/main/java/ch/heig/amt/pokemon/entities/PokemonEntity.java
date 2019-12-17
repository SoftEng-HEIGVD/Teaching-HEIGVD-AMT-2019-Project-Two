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
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class PokemonEntity {
    @Id
    @Column(name = "pokeDexId")
    private Integer pokeDex;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "category")
    private String category;

    @Column(name = "height")
    private Integer height;

    @Column(name = "hp")
    private Integer hp;
}
