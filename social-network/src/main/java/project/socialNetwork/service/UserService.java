package project.socialNetwork.service;

import project.socialNetwork.auth.request.SignupRequest;
import project.socialNetwork.model.entity.UserEntity;

import java.security.Principal;
import java.util.Optional;

public interface UserService {
    public Optional<UserEntity> findByUsername(String email);
    public UserEntity createUser(SignupRequest signupRequest);
    public UserEntity getCurrentUser(Principal principal);
}
