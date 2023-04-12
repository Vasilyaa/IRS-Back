package net.redciscso.javapmproject.dto;

import lombok.Data;

@Data
public class AnswerDto {
    private Long id;
    private String text;
    private Boolean isTrue;
}
