package org.poc.controllers;

import org.poc.DTOs.UserDTO;
import org.poc.services.interfaces.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static java.util.stream.Collectors.toList;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {

    @Inject
    private UserService userService;

    @GET
    public Response getAllUsers() {
        var result = userService.findAll().stream()
                .map(u -> UserDTO.builder().id(u.getId())
                    .name(u.getName())
                    .email(u.getEmail())
                    .createdAt(u.getCreatedAt())
                    .updatedAt(u.getUpdatedAt())).collect(toList());
        return Response.ok(result).build();
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
                               .updatedAt(user.getUpdatedAt()).build())
                    .status(Response.Status.CREATED).build();

        } catch (Exception e){
            return Response.ok(e.toString()).status(Response.Status.BAD_REQUEST).build();
        }
    }

}
