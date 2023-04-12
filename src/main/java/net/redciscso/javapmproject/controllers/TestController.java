package net.redciscso.javapmproject.controllers;

import lombok.RequiredArgsConstructor;
import net.redciscso.javapmproject.dto.SubjectDto;
import net.redciscso.javapmproject.dto.TestDto;
import net.redciscso.javapmproject.form.TestForm;
import net.redciscso.javapmproject.form.TestUpdateForm;
import net.redciscso.javapmproject.service.SubjectService;
import net.redciscso.javapmproject.service.TestService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(TestController.TEST_URI)
@RequiredArgsConstructor
public class TestController {

    public static final String TEST_URI = "/test";
    public static final String ADD_TEST_URI = "/add";
    public static final String DELETE_TEST_URI = "/delete/{id}";
    public static final String UPDATE_TEST_URI = "/update";
    public static final String All_BY_HEADER_ID_TEST_URI = "/all/{headerId}";
    private final TestService testService;

    @CrossOrigin
    @GetMapping(All_BY_HEADER_ID_TEST_URI)
    public List<TestDto> getAllByHeaderId(@PathVariable Long headerId) {
        return testService.getAllByHeaderId(headerId);
    }

    @CrossOrigin
    @PostMapping(ADD_TEST_URI)
    public TestDto add(@RequestBody TestForm testForm) {
        return testService.add(testForm);
    }

    @CrossOrigin
    @PostMapping(UPDATE_TEST_URI)
    public TestDto update(@RequestBody TestUpdateForm testUpdateForm) {
        return testService.update(testUpdateForm);
    }

    @CrossOrigin
    @GetMapping(DELETE_TEST_URI)
    public void delete(@PathVariable Long id) {
        testService.delete(id);
    }
}
