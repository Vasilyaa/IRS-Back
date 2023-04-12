package net.redciscso.javapmproject.mapper;

import net.redciscso.javapmproject.domain.testing.Answer;
import net.redciscso.javapmproject.domain.testing.Test;
import net.redciscso.javapmproject.dto.AnswerDto;
import net.redciscso.javapmproject.dto.TestDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AnswerMapper {
    private final ModelMapper mapper;

    public AnswerMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public AnswerDto toDto(Answer answer, Boolean isTeacher) {
        AnswerDto answerDto = new AnswerDto();
        answerDto.setId(answer.getId());
        answerDto.setText(answer.getText());
        answerDto.setIsTrue(isTeacher ? answer.getIsTrue() : false);
        return answerDto;
    }

    public List<AnswerDto> listToDto(List<Answer> answerList, Boolean isTeacher) {
        List<AnswerDto> answerDtoList = new ArrayList<>();
        for (Answer answer : answerList) {
            answerDtoList.add(toDto(answer, isTeacher));
        }
        return answerDtoList;
    }
}
