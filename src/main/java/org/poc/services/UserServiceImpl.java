package org.poc.services;

import org.poc.DTOs.UserDTO;
import org.poc.models.User;
import org.poc.repositories.interfaces.UserRepository;
import org.poc.services.interfaces.UserService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class UserServiceImpl implements UserService {

    @Inject
    UserRepository userRepository;


    @Override
    public List<User> findAll() {
        return this.userRepository.listAll();
    }

    @Override
    public User createUser(UserDTO dto) {
        var user = User.builder()
                .email(dto.getEmail())
                .name(dto.getName())
                .build();

        this.userRepository.persist(user);

        return user;
    }
}
