package net.redciscso.javapmproject.mapper;

import net.redciscso.javapmproject.domain.Subject;
import net.redciscso.javapmproject.domain.testing.Test;
import net.redciscso.javapmproject.dto.SubjectDto;
import net.redciscso.javapmproject.dto.TestDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TestMapper {
    private final ModelMapper mapper;

    public TestMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public TestDto toDto(Test test) {
        return mapper.map(test, TestDto.class);
    }

    public List<TestDto> listToDto(List<Test> testList) {
        List<TestDto> testDtoList = new ArrayList<>();
        for (Test test : testList) {
            testDtoList.add(toDto(test));
        }
        return testDtoList;
    }
}
