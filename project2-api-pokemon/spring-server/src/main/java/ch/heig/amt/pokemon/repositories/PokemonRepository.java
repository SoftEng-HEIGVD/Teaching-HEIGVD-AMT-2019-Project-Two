package ch.heig.amt.pokemon.repositories;

import ch.heig.amt.pokemon.entities.PokemonEntity;
import org.springframework.data.repository.CrudRepository;

public interface PokemonRepository extends CrudRepository<PokemonEntity, Integer> {

}
