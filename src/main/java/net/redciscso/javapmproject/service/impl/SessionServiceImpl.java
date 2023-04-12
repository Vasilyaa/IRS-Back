package net.redciscso.javapmproject.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.redciscso.javapmproject.domain.testing.*;
import net.redciscso.javapmproject.dto.SessionAnswerDto;
import net.redciscso.javapmproject.dto.SessionDto;
import net.redciscso.javapmproject.form.SessionForm;
import net.redciscso.javapmproject.form.SessionQuestionForm;
import net.redciscso.javapmproject.mapper.AnswerMapper;
import net.redciscso.javapmproject.repository.*;
import net.redciscso.javapmproject.service.SessionService;
import net.redciscso.javapmproject.service.UserService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@Primary
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {
    private final SessionRepository sessionRepository;
    private final AnswerRepository answerRepository;
    private final SessionAnswerRepository sessionAnswerRepository;
    private final TestRepository testRepository;
    private final QuestionRepository questionRepository;
    private final AnswerMapper answerMapper;

    private final UserService userService;

    @Override
    public SessionDto getSessionById(Long id) {
        Session session = sessionRepository.getOne(id);
        return null;
    }

    @Override
    public SessionDto add(SessionForm sessionForm) {
        Session session = new Session();
        session.setTest(testRepository.getOne(sessionForm.getTestId()));
        List<Session> userSessions = userService
                .getCurrentUser()
                .getSessions()
                .stream()
                .filter(e -> Objects.equals(e.getTest().getId(), sessionForm.getTestId()))
                .collect(Collectors.toList());
        if(userSessions.isEmpty()){
            session.setTryNum(1);
        }else{
            session.setTryNum(userSessions.size() + 1);
        }
        session.setUser(userService.getCurrentUser());

        session = sessionRepository.save(session);
        SessionDto sessionDto = new SessionDto();
        sessionDto.setId(session.getId());
        sessionDto.setTestName(session.getTest().getName());

        return sessionDto;
    }

    @Override
    public void addSessionQuestion(SessionQuestionForm sessionQuestionForm) {
        SessionQuestion sessionQuestion = new SessionQuestion();
        sessionQuestion.setQuestion(questionRepository.getOne(sessionQuestionForm.getQuestionId()));
        sessionQuestion.setSession(sessionRepository.getOne(sessionQuestionForm.getSessionId()));
        List<Answer> answers = new ArrayList<>();
        for(Long answerId: sessionQuestionForm.getAnswerIds()){
            answers.add(answerRepository.getOne(answerId));
        }
        sessionQuestion.setAnswers(answers);

        sessionQuestion.setIsTrue(answers.stream().allMatch(Answer::getIsTrue));

    }
}
