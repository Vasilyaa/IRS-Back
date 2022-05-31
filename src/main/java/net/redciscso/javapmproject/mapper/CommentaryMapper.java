package net.redciscso.javapmproject.mapper;

import net.redciscso.javapmproject.domain.Commentary;
import net.redciscso.javapmproject.dto.CommentaryDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommentaryMapper {
    private final ModelMapper mapper;

    public CommentaryMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public CommentaryDto toDto(Commentary Commentary) {
        return mapper.map(Commentary, CommentaryDto.class);
    }

    public List<CommentaryDto> listToDto(List<Commentary> CommentaryList) {
        List<CommentaryDto> commentaryDtoList = new ArrayList<>();
        for (Commentary Commentary : CommentaryList) {
            commentaryDtoList.add(mapper.map(Commentary, CommentaryDto.class));
        }
        return commentaryDtoList;
    }
}
