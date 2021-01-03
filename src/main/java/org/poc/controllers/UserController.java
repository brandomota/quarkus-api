package org.poc.controllers;

import org.poc.DTOs.UserDTO;
import org.poc.models.User;
import org.poc.services.interfaces.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {

    @Inject
    private UserService userService;

    @GET
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @POST
    public Response insertUser(UserDTO dto) {
        try {
            var user = userService.createUser(dto);

            return Response.ok(UserDTO.builder()
                               .id(user.getId())
                               .email(user.getEmail())
                               .name(user.getName())
                               .createdAt(user.getCreatedAt())
                               .updatedAt(user.getUpdatedAt()).build()).build();

        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

}
