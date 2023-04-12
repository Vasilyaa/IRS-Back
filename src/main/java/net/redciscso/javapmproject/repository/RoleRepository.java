package net.redciscso.javapmproject.repository;

import net.redciscso.javapmproject.domain.Role;
import net.redciscso.javapmproject.domain.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(RoleEnum name);
}

