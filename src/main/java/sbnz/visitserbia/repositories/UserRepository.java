package sbnz.visitserbia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sbnz.visitserbia.model.RegisteredUser;

import java.util.List;

public interface UserRepository extends JpaRepository<RegisteredUser, Long> {
	RegisteredUser findByEmail(String email);

	List<RegisteredUser> findByIsVerifiedFalse();

}
