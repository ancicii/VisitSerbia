package sbnz.visitserbia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sbnz.visitserbia.model.User;
import sbnz.visitserbia.model.VerificationToken;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    VerificationToken findByToken(String token);

    VerificationToken findByUser(User user);

}
