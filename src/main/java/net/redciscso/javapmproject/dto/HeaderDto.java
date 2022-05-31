package net.redciscso.javapmproject.dto;

import lombok.Data;
import net.redciscso.javapmproject.domain.Commentary;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Data
public class HeaderDto {
    private Long id;
    private Long articleId;
    private String name;
    private List<CommentaryDto> commentaries;
    private String createTime;
}
