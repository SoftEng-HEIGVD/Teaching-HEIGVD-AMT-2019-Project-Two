package spring.api.services;

import spring.api.exceptions.BadRequestException;
import spring.api.exceptions.ForbiddenException;
import spring.api.exceptions.NotFoundException;
import spring.model.Actor;

import java.net.URI;
import java.util.List;

public interface ActorsService {

    /**
     * Save actor in our db with owner's id and create resource for it in our api
     * @param actor the actor
     * @param ownerID the username of the owner of the resource
     * @return the location of the actor resource created
     */
    URI saveActor(Actor actor, String ownerID);

    /**
     * Return all actors owned by user.
     * @param ownerId username of the owner
     * @return the list of actors owned by user
     * @throws NotFoundException if no actors were for the user
     */
    List<Actor> findActorsByUser(String ownerId) throws NotFoundException;

    /**
     * Delete an actor owned by a user.
     * @param actorId id of the actor
     * @param requestOwner username of the owner
     * @throws NotFoundException if actor with actorId was not found
     * @throws ForbiddenException if trying to delete an actor owned by another user
     */
    void deleteActor(Long actorId, String requestOwner) throws NotFoundException, ForbiddenException;

    /**
     *
     * @param actorId id of the actor
     * @param requestOwner username of the request owner
     * @return the actor with actorId
     * @throws NotFoundException if actor with actorId was not found
     * @throws ForbiddenException if trying to get an actor owned by another user
     */
    Actor findActorById(Long actorId, String requestOwner) throws NotFoundException, ForbiddenException;

    /**
     * Update an actor in the database
     * @param actorId the id of the actor to be updated
     * @param updatedActor the updated actor dto
     * @param requestOwner the owner of the actor resource
     * @throws NotFoundException if actor was not found in db
     * @throws BadRequestException if actor dto was badly formed
     * @throws ForbiddenException if trying to update another user's actor
     */
    void updateActor(Long actorId, Actor updatedActor, String requestOwner) throws NotFoundException,
            BadRequestException, ForbiddenException;
}
