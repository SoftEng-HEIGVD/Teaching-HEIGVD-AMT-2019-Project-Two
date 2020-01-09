package heig.vd.ch.projet.travel.repositories;

import heig.vd.ch.projet.travel.entities.TripEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.util.Streamable;

import java.util.Collection;

public interface TripRepository extends CrudRepository<TripEntity, Integer> {
    Collection<TripEntity> findAllByEmailEquals(String string);
    Streamable<TripEntity> findAll();
}
