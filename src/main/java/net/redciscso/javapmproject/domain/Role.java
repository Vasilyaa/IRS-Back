package net.redciscso.javapmproject.domain;

import lombok.*;
import net.redciscso.javapmproject.domain.enums.RoleEnum;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * The entity was created to store access rights
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "roles")
@Builder
@AllArgsConstructor
public class Role {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * Role name
     *  example:
     *          STUDENT,ADMIN
     */
    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private RoleEnum name;

    /**
     * List of who has current role name
     */

    @ManyToMany(mappedBy = "roles",fetch = FetchType.EAGER)
    private List<User> users;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Role role = (Role) o;
        return id != null && Objects.equals(id, role.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

