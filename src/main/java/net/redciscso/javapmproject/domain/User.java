package net.redciscso.javapmproject.domain;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * List of user's roles
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable(joinColumns = {
            @JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<Role> roles;
    /**
     *  Username or login value
     */
    @Column(length = 100, unique = true)
    private String username;
    /**
     *  Full name value
     */
    @Column(length = 100, unique = true)
    private String fullName;
    /**
     *  Hash value of password
     */
    private String password;
    /**
     * List of tokens for auth in application
     */
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    List<Token> tokens;

}

