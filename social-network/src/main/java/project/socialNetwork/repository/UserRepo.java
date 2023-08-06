package project.socialNetwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.socialNetwork.model.entity.UserEntity;

import java.util.Optional;

public interface UserRepo extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
}
