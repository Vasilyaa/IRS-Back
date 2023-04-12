package net.redciscso.javapmproject.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.redciscso.javapmproject.domain.testing.Answer;
import net.redciscso.javapmproject.domain.testing.Question;
import net.redciscso.javapmproject.domain.testing.Test;
import net.redciscso.javapmproject.dto.QuestionDto;
import net.redciscso.javapmproject.form.AnswerForm;
import net.redciscso.javapmproject.form.QuestionForm;
import net.redciscso.javapmproject.mapper.QuestionMapper;
import net.redciscso.javapmproject.repository.AnswerRepository;
import net.redciscso.javapmproject.repository.QuestionRepository;
import net.redciscso.javapmproject.repository.TestRepository;
import net.redciscso.javapmproject.service.QuestionService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@Primary
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;
    private final TestRepository testRepository;
    private final AnswerRepository answerRepository;
    private final QuestionMapper questionMapper;

    @Override
    public List<QuestionDto> getAllByTestId(Long testId, Boolean isTeacher) {
        return questionMapper.listToDto(questionRepository.findAllByTestId(testId), isTeacher);
    }

    @Override
    public QuestionDto add(QuestionForm questionForm) {
        Test test = testRepository.getOne(questionForm.getTestId());

        Question question = new Question();
        question.setText(questionForm.getText());
        question.setTest(test);
        question.setScores(questionForm.getScores());

        question = questionRepository.save(question);

        for(AnswerForm answerForm: questionForm.getAnswers()){
            Answer answer = new Answer();
            answer.setQuestion(question);
            answer.setIsTrue(answerForm.getIsTrue());
            answer.setText(answerForm.getText());
            answerRepository.save(answer);
        }


        return questionMapper.toDto(questionRepository.getOne(question.getId()), true);
    }
}
