package spring.api.services;

import spring.api.exceptions.ApiException;
import spring.model.Role;

import java.net.URI;
import java.util.List;

public interface RolesService {

    URI saveRole(Role role, String requestOwner) throws ApiException;

    void deleteRole(Long roleId, String requestOwner) throws ApiException;

    List<Role> getAllRolesByActor(Long actorId, String requestOwner) throws ApiException;

    List<Role> getAllRolesByMovie(Long movieId, String requestOwner) throws ApiException;
}
