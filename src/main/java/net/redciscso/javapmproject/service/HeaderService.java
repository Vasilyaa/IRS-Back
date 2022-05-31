package net.redciscso.javapmproject.service;

import net.redciscso.javapmproject.dto.HeaderDto;
import net.redciscso.javapmproject.form.HeaderForm;
import net.redciscso.javapmproject.form.HeaderUpdateForm;

import java.util.List;

public interface HeaderService {
    List<HeaderDto> getAllHeaders();

    List<HeaderDto> getAllContains(String request);

    HeaderDto add(HeaderForm headerForm);

    HeaderDto update(HeaderUpdateForm headerUpdateForm);

    void delete(Long id);
}
