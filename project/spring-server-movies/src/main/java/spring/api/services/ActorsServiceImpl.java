package spring.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import spring.api.exceptions.BadRequestException;
import spring.api.exceptions.ForbiddenException;
import spring.api.exceptions.NotFoundException;
import spring.entities.ActorEntity;
import spring.model.Actor;
import spring.repositories.ActorsRepository;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Service
public class ActorsServiceImpl implements ActorsService {

    @Autowired
    ActorsRepository actorsRepository;

    @Autowired
    DtoConverter dtoConverter;

    @Override
    public URI saveActor(Actor actor, String ownerID) {
        ActorEntity actorEntity = dtoConverter.toActorEntity(actor);
        actorEntity.setOwnerId(ownerID);
        actorsRepository.save(actorEntity);
        Long id = actorEntity.getId();

        return ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(actorEntity.getId()).toUri();
    }

    @Override
    public List<Actor> findActorsByUser(String ownerId, int page, int pageSize) throws NotFoundException {
        List<Actor> actors = new ArrayList<>();
        for (ActorEntity actorEntity : actorsRepository.findAll(PageRequest.of(page, pageSize))) {
            if(actorEntity.getOwnerId().equals(ownerId)) {
                actors.add(dtoConverter.toActor(actorEntity));
            }
        }

        if (actors.isEmpty()) {
            throw new NotFoundException("User doesn't own any actor yet");
        }
        return actors;
    }

    @Override
    public void deleteActor(Long actorId, String requestOwner) throws NotFoundException, ForbiddenException {
    ActorEntity actorEntity = actorsRepository.findById(actorId)
            .orElseThrow(() -> new NotFoundException("Could not find actor with actor id " + actorId));

        if (!actorEntity.getOwnerId().equals(requestOwner)) {
            throw new ForbiddenException("Cannot delete another's user actor");
        }

        actorsRepository.deleteById(actorId);
    }

    @Override
    public Actor findActorById(Long actorId, String requestOwner) throws NotFoundException, ForbiddenException {

        ActorEntity actorEntity = actorsRepository.findById(actorId)
                .orElseThrow(() -> new NotFoundException("Could not find actor with actor id " + actorId));

        if (!actorEntity.getOwnerId().equals(requestOwner)) {
            throw new ForbiddenException("Cannot retrieve another's user actor");
        }

        return dtoConverter.toActor(actorEntity);
    }

    @Override
    public void updateActor(Long actorId, Actor updatedActor, String requestOwner) throws NotFoundException, BadRequestException, ForbiddenException {
        ActorEntity actorEntity = actorsRepository.findById(actorId)
                .orElseThrow(() -> new NotFoundException("Could not find actor with actor id " + actorId));

        if (!actorEntity.getOwnerId().equals(requestOwner)) {
            throw new ForbiddenException("Cannot update another's user actor");
        }

        ActorEntity updatedActorEntity = dtoConverter.toActorEntity(updatedActor);
        // update original properties
        actorEntity.setFirstname(updatedActorEntity.getFirstname());
        actorEntity.setLastname(updatedActorEntity.getLastname());
        actorEntity.setExpertise(updatedActorEntity.getExpertise());
        actorsRepository.save(actorEntity);
    }
}

