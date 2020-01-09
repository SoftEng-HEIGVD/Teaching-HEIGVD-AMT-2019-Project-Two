package heig.vd.ch.projet.travel.repositories;

import heig.vd.ch.projet.travel.entities.CountryEntity;
import org.springframework.data.repository.CrudRepository;

public interface CountryRepository extends CrudRepository<CountryEntity, Integer> {
}
