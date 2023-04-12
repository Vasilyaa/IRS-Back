package net.redciscso.javapmproject.domain.testing;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "answer")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name ="text")
    private String text;

    @Column(name = "is_true")
    private Boolean isTrue;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @ManyToOne
    @JoinColumn(name = "session_question_id")
    private SessionQuestion sessionQuestion;
}
