package spring.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import spring.api.exceptions.ApiException;
import spring.api.exceptions.ForbiddenException;
import spring.api.exceptions.NotFoundException;
import spring.entities.ActorEntity;
import spring.entities.MovieEntity;
import spring.entities.RoleEntity;
import spring.model.Role;
import spring.repositories.ActorsRepository;
import spring.repositories.MoviesRepository;
import spring.repositories.RoleRepository;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Service
public class RolesServiceImpl implements RolesService {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    MoviesRepository moviesRepository;

    @Autowired
    ActorsRepository actorsRepository;

    @Autowired
    DtoConverter dtoConverter;

    @Override
    public URI saveRole(Role role, String requestOwner) throws ApiException {
        RoleEntity roleEntity = dtoConverter.toRoleEntity(role);

        MovieEntity movieEntity = moviesRepository.findById(role.getMovieId())
                .orElseThrow(() -> new NotFoundException("No movie with movie id" + role .getMovieId() + " found"));

        ActorEntity actorEntity = actorsRepository.findById(role.getActorId())
                .orElseThrow(() -> new NotFoundException("No actor with actor id" + role.getActorId() + " found"));

        if (!actorEntity.getOwnerId().equals(requestOwner)) {
            throw new ForbiddenException("Not allowed to cast an actor you're not associated with");
        }

        if (!movieEntity.getOwnerId().equals(requestOwner)) {
            throw new ForbiddenException("Not allowed to cast an actor in a movie you're not associated with");
        }

        roleEntity.setActorEntity(actorEntity);
        roleEntity.setMovieEntity(movieEntity);
        roleRepository.save(roleEntity);

        Long id = roleEntity.getId();

        return ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(roleEntity.getId()).toUri();
    }

    @Override
    public void deleteRole(Long actorId, Long movieId, String requestOwner) throws ApiException {
        RoleEntity roleEntity = roleRepository.findByActorEntity_IdAndMovieEntity_Id(actorId, movieId)
                .orElseThrow(() -> new NotFoundException("No role with actor id " + actorId
                        + " and movie id " + movieId + " found"));

        if (roleEntity.getActorEntity().getOwnerId().equals(requestOwner)
                && roleEntity.getMovieEntity().getOwnerId().equals(requestOwner)) {
            roleRepository.delete(roleEntity);
        } else {
            throw new ForbiddenException("Cannot delete a role you're not associated with");
        }
    }

    @Override
    public List<Role> getAllRolesByActor(Long actorId, String requestOwner, int page, int pageSize) throws ApiException {
        ActorEntity actorEntity = actorsRepository.findById(actorId)
                .orElseThrow(() -> new NotFoundException("No actor with actor id" + actorId + " found"));

        if(!actorEntity.getOwnerId().equals(requestOwner)) {
            throw new ForbiddenException("Not allowed to see the roles of an actor you're not associated with");
        }

        List<Role> rolesList = new ArrayList<>();

        for (RoleEntity roleEntity: roleRepository.findByActorEntity_Id(actorId, PageRequest.of(page, pageSize))) {
            rolesList.add(dtoConverter.toRole(roleEntity));
        }

        if (rolesList.isEmpty()) {
            throw new NotFoundException("Actor doesn't have any roles yet");
        }

        return rolesList;
    }

    @Override
    public List<Role> getAllRolesByMovie(Long movieId, String requestOwner, int page, int pageSize) throws ApiException {
        MovieEntity movieEntity = moviesRepository.findById(movieId)
                .orElseThrow(() -> new NotFoundException("No movie with movie id" + movieId + " found"));

        if(!movieEntity.getOwnerId().equals(requestOwner)) {
            throw new ForbiddenException("Not allowed to see the castings of a movie you're not associated with");
        }

        List<Role> rolesList = new ArrayList<>();

        for (RoleEntity roleEntity: roleRepository.findByMovieEntity_Id(movieId, PageRequest.of(page, pageSize))) {
            rolesList.add(dtoConverter.toRole(roleEntity));
        }

        if (rolesList.isEmpty()) {
            throw new NotFoundException("Movie doesn't have any castings yet");
        }

        return rolesList;
    }
}
