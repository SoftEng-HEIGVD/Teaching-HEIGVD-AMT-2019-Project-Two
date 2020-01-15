package heig.vd.ch.projet.travel.repositories;

import heig.vd.ch.projet.travel.entities.ReasonEntity;
import org.springframework.data.util.Streamable;
import org.springframework.data.repository.CrudRepository;

public interface ReasonRepository extends CrudRepository<ReasonEntity, Integer> {
    @Override
    Streamable<ReasonEntity> findAll();
}
