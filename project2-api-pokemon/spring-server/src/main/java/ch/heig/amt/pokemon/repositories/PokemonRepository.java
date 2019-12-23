package ch.heig.amt.pokemon.repositories;

import ch.heig.amt.pokemon.entities.PokemonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PokemonRepository extends CrudRepository<PokemonEntity, Integer> {
    Optional<PokemonEntity> findBypokeDexId(Integer id);
}
