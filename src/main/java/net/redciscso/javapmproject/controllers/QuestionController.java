package net.redciscso.javapmproject.controllers;

import lombok.RequiredArgsConstructor;
import net.redciscso.javapmproject.dto.QuestionDto;
import net.redciscso.javapmproject.dto.TestDto;
import net.redciscso.javapmproject.form.QuestionForm;
import net.redciscso.javapmproject.form.TestForm;
import net.redciscso.javapmproject.service.QuestionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(QuestionController.QUESTION_URI)
@RequiredArgsConstructor
public class QuestionController {
    public static final String QUESTION_URI = "/question";
    public static final String ALL_BY_TEST_ID_STUDENT_QUESTION_URI = "/student/all/{testId}";

    public static final String ALL_BY_TEST_ID_TEACHER_QUESTION_URI = "/teacher/all/{testId}";
    public static final String ADD_QUESTION_URI = "/add";

    private final QuestionService questionService;

    @CrossOrigin
    @GetMapping(ALL_BY_TEST_ID_STUDENT_QUESTION_URI)
    public List<QuestionDto> getAllByTestIdForStudent(@PathVariable Long testId) {
        return questionService.getAllByTestId(testId, false);
    }

    @CrossOrigin
    @GetMapping(ALL_BY_TEST_ID_TEACHER_QUESTION_URI)
    public List<QuestionDto> getAllByTestIdForTeacher(@PathVariable Long testId) {
        return questionService.getAllByTestId(testId, true);
    }

    @CrossOrigin
    @PostMapping(ADD_QUESTION_URI)
    public QuestionDto add(@RequestBody QuestionForm questionForm) {
        return questionService.add(questionForm);
    }
}
