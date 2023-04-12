package net.redciscso.javapmproject.dto;

import lombok.Data;

import java.util.List;

@Data
public class SessionQuestionDto {
    private QuestionDto question;
    private Boolean isTrue;
    private List<AnswerDto> answers;
}
