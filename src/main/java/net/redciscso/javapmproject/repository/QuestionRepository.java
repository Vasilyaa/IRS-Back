package net.redciscso.javapmproject.repository;

import net.redciscso.javapmproject.domain.testing.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findAllByTestId(Long testId);
}
