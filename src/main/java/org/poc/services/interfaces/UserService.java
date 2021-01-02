package org.poc.services.interfaces;

import org.poc.models.User;

import java.util.List;


public interface UserService {
    List<User> findAll();
}
