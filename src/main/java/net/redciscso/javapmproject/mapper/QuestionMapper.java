package net.redciscso.javapmproject.mapper;

import net.redciscso.javapmproject.domain.testing.Answer;
import net.redciscso.javapmproject.domain.testing.Question;
import net.redciscso.javapmproject.dto.QuestionDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class QuestionMapper {
    private final AnswerMapper answerMapper;
    private final ModelMapper mapper;

    public QuestionMapper(AnswerMapper answerMapper, ModelMapper mapper) {
        this.answerMapper = answerMapper;
        this.mapper = mapper;
    }

    public QuestionDto toDto(Question question, Boolean isTeacher) {
        QuestionDto questionDto = new QuestionDto();
        questionDto.setId(question.getId());
        questionDto.setScores(question.getScores());
        questionDto.setIsManyAnswer(question.getAnswers().stream().filter(Answer::getIsTrue).count() > 1);
        questionDto.setAnswers(answerMapper.listToDto(question.getAnswers(), isTeacher));
        return mapper.map(question, QuestionDto.class);
    }

    public List<QuestionDto> listToDto(List<Question> questionList, Boolean isTeacher) {
        List<QuestionDto> questionDtoList = new ArrayList<>();
        for (Question question : questionList) {
            questionDtoList.add(toDto(question, isTeacher));
        }
        return questionDtoList;
    }
}
