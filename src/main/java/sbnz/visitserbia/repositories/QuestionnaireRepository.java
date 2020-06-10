package sbnz.visitserbia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sbnz.visitserbia.model.Question;

public interface QuestionnaireRepository extends JpaRepository<Question, Long> {


}
