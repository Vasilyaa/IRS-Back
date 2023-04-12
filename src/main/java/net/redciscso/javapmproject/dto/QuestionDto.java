package net.redciscso.javapmproject.dto;

import lombok.Data;

import java.util.List;

@Data
public class QuestionDto {
    private Long id;
    private Boolean isManyAnswer;
    private Integer scores;
    private List<AnswerDto> answers;
}
