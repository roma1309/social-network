package project.socialNetwork.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.socialNetwork.model.entity.User;
import project.socialNetwork.repository.UserRepo;
import project.socialNetwork.service.UserService;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public Optional<User> findByUsername(String email) {
        return userRepo.findByEmail(email);
    }
}
