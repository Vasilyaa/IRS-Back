package net.redciscso.javapmproject.controllers;

import lombok.RequiredArgsConstructor;
import net.redciscso.javapmproject.dto.SessionDto;
import net.redciscso.javapmproject.dto.TestDto;
import net.redciscso.javapmproject.form.SessionForm;
import net.redciscso.javapmproject.form.SessionQuestionForm;
import net.redciscso.javapmproject.form.TestForm;
import net.redciscso.javapmproject.form.TestUpdateForm;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(SessionController.SESSION_URI)
@RequiredArgsConstructor
public class SessionController {
    public static final String SESSION_URI = "/session";
    public static final String ADD_SESSION_URI = "/add";
    public static final String GET_SESSION_BY_ID_URI = "/{id}";
    public static final String ADD_SESSION_QUESTION_URI = "/question/add";

    @CrossOrigin
    @GetMapping(GET_SESSION_BY_ID_URI)
    public SessionDto getSessionById(@PathVariable Long id) {
        return null;
    }

    @CrossOrigin
    @PostMapping(ADD_SESSION_URI)
    public SessionDto add(@RequestBody SessionForm sessionForm) {
        return null;
    }

    @CrossOrigin
    @PostMapping(ADD_SESSION_QUESTION_URI)
    public void addSessionQuestion(@RequestBody SessionQuestionForm sessionQuestionForm) {

    }
}
