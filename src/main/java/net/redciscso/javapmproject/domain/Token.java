package net.redciscso.javapmproject.domain;

import lombok.*;

import javax.persistence.*;

/**
 * Token entity . This entity contains token value,
 * which using for authentication in application
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Token{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * The value of the token
     * example:
     *          hMSnXq4NkTwG5Lv5H1vLCGKo8G1xx5fckhBix2evitHG7jnMitnEDZgFOFcm7cmkT1topo9OwgxeD17m
     */



    private String value;

    /**
     * Link to the token owner
     */

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}

