package org.poc.services;

import org.poc.models.User;
import org.poc.repositories.interfaces.UserRepository;
import org.poc.services.interfaces.UserService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class UserServiceImpl implements UserService {

    @Inject
    private UserRepository userRepository;


    @Override
    public List<User> findAll() {
        return userRepository.listAll();
    }
}
