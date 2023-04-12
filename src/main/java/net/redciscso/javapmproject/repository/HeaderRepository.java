package net.redciscso.javapmproject.repository;

import net.redciscso.javapmproject.domain.Header;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HeaderRepository extends JpaRepository<Header, Long> {

    //найти все хэдеры и отсортировать их по дате создания
    List<Header> findAllByOrderByCreateTime();

    boolean existsById(Long id);

//вернуть все хэдеры у которых в имени содержатся строка request
    List<Header> findAllByNameContainsAndSubjectId(String request, Long subjectId);

    List<Header> findAllBySubjectId(Long subjectId);
}
