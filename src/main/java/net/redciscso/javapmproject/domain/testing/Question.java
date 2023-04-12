package net.redciscso.javapmproject.domain.testing;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name ="text")
    private String text;

    @Column(name = "scores")
    private Integer scores;

    @ManyToOne
    @JoinColumn(name = "test_id")
    private Test test;

    @OneToMany(mappedBy = "question",cascade = CascadeType.ALL)
    private List<Answer> answers;

    @OneToMany(mappedBy = "question",cascade = CascadeType.ALL)
    private List<SessionQuestion> sessionQuestions;
}
