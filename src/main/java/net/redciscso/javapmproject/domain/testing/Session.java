package net.redciscso.javapmproject.domain.testing;

import lombok.Getter;
import lombok.Setter;
import net.redciscso.javapmproject.domain.User;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "session")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "try_num")
    private Integer tryNum;

    @Column(name = "common_scores")
    private Integer commonScores;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "test_id")
    private Test test;

    @OneToMany(mappedBy = "session",cascade = CascadeType.ALL)
    private List<SessionQuestion> sessionQuestions;
}
