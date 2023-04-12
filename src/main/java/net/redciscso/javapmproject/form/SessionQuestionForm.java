package net.redciscso.javapmproject.form;

import lombok.Data;

import java.util.List;

@Data
public class SessionQuestionForm {
    private Long sessionId;
    private Long questionId;
    private List<Long> answerIds;
}
