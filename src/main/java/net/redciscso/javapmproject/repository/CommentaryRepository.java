package net.redciscso.javapmproject.repository;

import net.redciscso.javapmproject.domain.Commentary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentaryRepository extends JpaRepository<Commentary, Long> {
}
