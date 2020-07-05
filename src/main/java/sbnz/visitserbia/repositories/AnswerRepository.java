package sbnz.visitserbia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sbnz.visitserbia.model.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
