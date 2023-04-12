package net.redciscso.javapmproject.dto;

import lombok.Data;

import java.util.List;

@Data
public class SessionAnswerDto {
    private QuestionDto question;
    private AnswerDto answer;
}
