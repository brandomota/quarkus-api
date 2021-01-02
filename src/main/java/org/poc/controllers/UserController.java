package org.poc.controllers;

import org.jboss.resteasy.spi.NotImplementedYetException;
import org.poc.DTOs.UserDTO;
import org.poc.models.User;
import org.poc.services.interfaces.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
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
    public User insertUser(UserDTO dto) {
        throw new NotImplementedYetException();
    }

}
