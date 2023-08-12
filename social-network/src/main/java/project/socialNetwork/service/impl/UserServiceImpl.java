package project.socialNetwork.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.socialNetwork.auth.request.SignupRequest;
import project.socialNetwork.exceptions.UserExistException;
import project.socialNetwork.model.entity.UserEntity;
import project.socialNetwork.model.enums.Role;
import project.socialNetwork.repository.UserRepo;
import project.socialNetwork.service.UserService;

import java.security.Principal;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    public static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<UserEntity> findByUsername(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public UserEntity createUser(SignupRequest signupRequest) {
        if (!signupRequest.getConfirmPassword().equals(signupRequest.getPassword())) {
            throw new UserExistException("Please check password and confirmPassword");
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(signupRequest.getEmail());
        userEntity.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        userEntity.setEmail(signupRequest.getEmail());
        userEntity.setRole(Role.ROLE_USER);
        System.out.println(signupRequest.getConfirmPassword());
        userEntity.setLastname(signupRequest.getLastname());
        userEntity.setFirstname(signupRequest.getFirstname());
        try {
            LOG.info("Saving user {}", signupRequest.getEmail());
            return userRepo.save(userEntity);
        } catch (Exception e) {
            LOG.error("Error during registration. {}", e.getMessage());
            throw new UserExistException("The user" + signupRequest.getEmail() + "already exist. Please check credentials");
        }
    }

    @Override
    public UserEntity getCurrentUser(Principal principal) {
        return getUserByPrincipal(principal);
    }

    private UserEntity getUserByPrincipal(Principal principal) {
      //  System.out.println(principal.getName());
        String username = principal.getName();
        UserEntity userEntity = userRepo.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Username not found with username - " + username));
        return userEntity;
    }
}
