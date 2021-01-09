package org.poc.services.interfaces;

import org.poc.DTOs.UserDTO;
import org.poc.models.User;

import java.util.List;


public interface UserService {
    List<User> findAll();
    User createUser(UserDTO dto) throws Exception;
}
