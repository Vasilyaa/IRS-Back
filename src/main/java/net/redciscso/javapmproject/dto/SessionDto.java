package net.redciscso.javapmproject.dto;

import lombok.Data;

import java.util.List;

@Data
public class SessionDto {
    private Long id;
    private Integer commonScores;
    private String testName;
    private List<SessionQuestionDto> sessionQuestions;
}
