package net.redciscso.javapmproject.controllers;

import lombok.RequiredArgsConstructor;
import net.redciscso.javapmproject.dto.SubjectDto;
import net.redciscso.javapmproject.service.SubjectService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(SubjectController.SUBJECT_URI)
@RequiredArgsConstructor
public class SubjectController {
    public static final String SUBJECT_URI = "/subject";
    public static final String ALL_SUBJECT_URI = "/all";

    private final SubjectService subjectService;

    @CrossOrigin
    @GetMapping(ALL_SUBJECT_URI)
    public List<SubjectDto> getAll() {
        return subjectService.getAll();
    }

}
