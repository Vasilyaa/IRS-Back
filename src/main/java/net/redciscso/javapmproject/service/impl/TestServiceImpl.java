package net.redciscso.javapmproject.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.redciscso.javapmproject.domain.Header;
import net.redciscso.javapmproject.domain.testing.Test;
import net.redciscso.javapmproject.dto.TestDto;
import net.redciscso.javapmproject.form.TestForm;
import net.redciscso.javapmproject.form.TestUpdateForm;
import net.redciscso.javapmproject.mapper.TestMapper;
import net.redciscso.javapmproject.repository.HeaderRepository;
import net.redciscso.javapmproject.repository.TestRepository;
import net.redciscso.javapmproject.service.TestService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@Primary
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {
    private final TestRepository testRepository;
    private final HeaderRepository headerRepository;
    private final TestMapper testMapper;

    @Override
    public List<TestDto> getAllByHeaderId(Long headerId) {
        return testMapper.listToDto(testRepository.findAllByHeaderId(headerId));
    }

    @Override
    public TestDto add(TestForm testForm) {
        Test test = new Test();
        Header header = headerRepository.getOne(testForm.getHeaderId());
        test.setName(testForm.getName());
        test.setHeader(header);
        return testMapper.toDto(testRepository.save(test));
    }

    @Override
    public TestDto update(TestUpdateForm testUpdateForm) {
        Test test = testRepository.getOne(testUpdateForm.getId());
        test.setName(testUpdateForm.getName() != null ? testUpdateForm.getName() : test.getName());
        test.setTryCount(testUpdateForm.getTryCount() != null ? testUpdateForm.getTryCount() : test.getTryCount());
        return testMapper.toDto(testRepository.save(test));
    }

    @Override
    public void delete(Long id) {
        Test test = testRepository.getOne(id);
        testRepository.delete(test);
    }
}
