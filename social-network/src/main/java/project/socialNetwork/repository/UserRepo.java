package project.socialNetwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.socialNetwork.model.entity.User;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
}
