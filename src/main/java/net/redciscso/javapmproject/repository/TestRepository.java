package net.redciscso.javapmproject.repository;

import net.redciscso.javapmproject.domain.testing.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {
    List<Test> findAllByHeaderId(Long headerId);
}
