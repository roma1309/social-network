package project.socialNetwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.socialNetwork.model.entity.User;

public interface UserRepo extends JpaRepository<User,Long> {
}
