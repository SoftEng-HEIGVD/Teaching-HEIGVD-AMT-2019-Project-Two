package heig.vd.ch.projet.travel.repositories;

import heig.vd.ch.projet.travel.entities.CountryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.util.Streamable;


public interface CountryRepository extends CrudRepository<CountryEntity, Integer> {
    @Override
    Streamable<CountryEntity> findAll();
}
