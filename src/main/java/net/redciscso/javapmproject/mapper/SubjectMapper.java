package net.redciscso.javapmproject.mapper;

import net.redciscso.javapmproject.domain.Subject;
import net.redciscso.javapmproject.dto.SubjectDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SubjectMapper {
    private final ModelMapper mapper;

    public SubjectMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public SubjectDto toDto(Subject subject) {
        return mapper.map(subject, SubjectDto.class);
    }

    public List<SubjectDto> listToDto(List<Subject> subjectList) {
        List<SubjectDto> subjectDtoList = new ArrayList<>();
        for (Subject subject : subjectList) {
            subjectDtoList.add(toDto(subject));
        }
        return subjectDtoList;
    }
}
