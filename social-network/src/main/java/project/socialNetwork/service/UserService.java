package project.socialNetwork.service;

import project.socialNetwork.model.entity.User;

import java.util.Optional;

public interface UserService {
    public Optional<User> findByUsername(String email);
}
