package net.redciscso.javapmproject.service;

import net.redciscso.javapmproject.dto.TestDto;
import net.redciscso.javapmproject.form.TestForm;
import net.redciscso.javapmproject.form.TestUpdateForm;

import java.util.List;

public interface TestService {
    List<TestDto> getAllByHeaderId(Long headerId);

    TestDto add(TestForm testForm);

    TestDto update(TestUpdateForm testUpdateForm);

    void delete(Long id);
}
