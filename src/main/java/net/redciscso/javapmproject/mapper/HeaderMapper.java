package net.redciscso.javapmproject.mapper;

import net.redciscso.javapmproject.domain.Header;
import net.redciscso.javapmproject.dto.HeaderDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class HeaderMapper {
    private final ModelMapper mapper;
    private final CommentaryMapper commentaryMapper;
    private static final String DATE_FORMAT_PATTERN = "dd.MM.yyyy";

    public HeaderMapper(ModelMapper mapper, CommentaryMapper commentaryMapper) {
        this.mapper = mapper;
        this.commentaryMapper = commentaryMapper;
    }

    public HeaderDto toDto(Header header) {
        HeaderDto headerDto = mapper.map(header, HeaderDto.class);
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_PATTERN);
        headerDto.setCreateTime(dateFormat.format(header.getCreateTime()));
        headerDto.setArticleId(header.getArticle().getId());
        if(header.getCommentaries() != null && !header.getCommentaries().isEmpty()){
            headerDto.setCommentaries(commentaryMapper.listToDto(header.getCommentaries()));
        }
        return headerDto;
    }

    public List<HeaderDto> listToDto(List<Header> headerList) {
        List<HeaderDto> headerDtoList = new ArrayList<>();
        for (Header header : headerList) {
            headerDtoList.add(toDto(header));
        }
        return headerDtoList;
    }
}
