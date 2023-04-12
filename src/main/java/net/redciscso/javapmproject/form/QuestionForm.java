package net.redciscso.javapmproject.form;

import lombok.Data;

import java.util.List;

@Data
public class QuestionForm {
    private Long testId;
    private String text;
    private Integer scores;
    private List<AnswerForm> answers;
}
