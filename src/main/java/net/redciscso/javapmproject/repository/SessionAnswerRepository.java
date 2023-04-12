package net.redciscso.javapmproject.repository;

import net.redciscso.javapmproject.domain.testing.SessionAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionAnswerRepository extends JpaRepository<SessionAnswer, Long> {
}
