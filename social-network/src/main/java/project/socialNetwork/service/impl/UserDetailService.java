package project.socialNetwork.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.socialNetwork.model.entity.User;
import project.socialNetwork.repository.UserRepo;
import project.socialNetwork.service.UserService;

import java.util.Collection;

@Service
public class UserDetailService implements UserDetailsService {
    private final UserService userService;

    @Autowired
    public UserDetailService(UserService userService) {
        this.userService = userService;
    }
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", username)));
        return user;
    }
}
