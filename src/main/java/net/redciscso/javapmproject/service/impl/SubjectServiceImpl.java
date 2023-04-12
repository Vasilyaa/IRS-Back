package net.redciscso.javapmproject.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.redciscso.javapmproject.dto.SubjectDto;
import net.redciscso.javapmproject.mapper.SubjectMapper;
import net.redciscso.javapmproject.repository.SubjectRepository;
import net.redciscso.javapmproject.service.SubjectService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@Primary
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {
    private final SubjectRepository subjectRepository;

    private final SubjectMapper subjectMapper;
    @Override
    public List<SubjectDto> getAll() {
        return subjectMapper.listToDto(
                subjectRepository.findAll()
        );
    }

    @Override
    public SubjectDto getById(Long id) {
        return subjectMapper.toDto(subjectRepository.getOne(id));
    }
}
