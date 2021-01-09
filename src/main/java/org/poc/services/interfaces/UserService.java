package org.poc.services.interfaces;

import org.poc.DTOs.UserDTO;
import org.poc.models.User;

import java.util.List;
import java.util.Map;


public interface UserService {
    List<User> findAll();
    User createUser(UserDTO dto) throws Exception;
    List<User> findUsers(Map<String, String> query);
}
