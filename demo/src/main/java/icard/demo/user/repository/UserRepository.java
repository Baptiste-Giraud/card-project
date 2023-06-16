package icard.demo.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import icard.demo.user.model.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
}
