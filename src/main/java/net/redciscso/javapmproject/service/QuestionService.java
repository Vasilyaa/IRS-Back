package net.redciscso.javapmproject.service;

import net.redciscso.javapmproject.dto.QuestionDto;
import net.redciscso.javapmproject.form.QuestionForm;

import java.util.List;

public interface QuestionService {
    List<QuestionDto> getAllByTestId(Long testId, Boolean isTeacher);

    QuestionDto add(QuestionForm questionForm);
}
