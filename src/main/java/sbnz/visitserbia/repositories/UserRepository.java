package sbnz.visitserbia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sbnz.visitserbia.model.RegisteredUser;
import sbnz.visitserbia.model.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);

	List<User> findByIsVerifiedFalse();

	RegisteredUser findRegUserByEmail(String email);
}
