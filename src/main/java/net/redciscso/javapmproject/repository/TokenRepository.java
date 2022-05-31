package net.redciscso.javapmproject.repository;

import net.redciscso.javapmproject.domain.Token;
import net.redciscso.javapmproject.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token,Long> {

    //получаем токен по значению которое передали
    Optional<Token> findOneByValue(String value);


}

