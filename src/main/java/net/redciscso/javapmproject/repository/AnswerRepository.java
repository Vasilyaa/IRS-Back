package net.redciscso.javapmproject.repository;

import net.redciscso.javapmproject.domain.testing.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

}
