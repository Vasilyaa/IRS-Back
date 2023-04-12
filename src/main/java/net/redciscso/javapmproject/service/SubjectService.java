package net.redciscso.javapmproject.service;

import net.redciscso.javapmproject.dto.SubjectDto;

import java.util.List;

public interface SubjectService {
    List<SubjectDto> getAll();

    SubjectDto getById(Long id);
}
