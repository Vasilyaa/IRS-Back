package net.redciscso.javapmproject.repository;

import net.redciscso.javapmproject.domain.testing.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {

}
