package net.redciscso.javapmproject.service;

import net.redciscso.javapmproject.dto.SessionDto;
import net.redciscso.javapmproject.form.SessionForm;
import net.redciscso.javapmproject.form.SessionQuestionForm;

public interface SessionService {
    SessionDto getSessionById(Long id);

    SessionDto add(SessionForm sessionForm);

    void addSessionQuestion(SessionQuestionForm sessionQuestionForm);
}
