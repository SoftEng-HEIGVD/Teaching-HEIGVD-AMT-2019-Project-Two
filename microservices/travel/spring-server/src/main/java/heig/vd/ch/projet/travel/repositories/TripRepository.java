package heig.vd.ch.projet.travel.repositories;

import heig.vd.ch.projet.travel.entities.CountryEntity;
import heig.vd.ch.projet.travel.entities.TripEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.util.Streamable;

import java.util.Collection;
import java.util.Optional;

public interface TripRepository extends CrudRepository<TripEntity, Integer> {
    Page<TripEntity> findAllByEmailEquals(String string, Pageable pageable);
}
