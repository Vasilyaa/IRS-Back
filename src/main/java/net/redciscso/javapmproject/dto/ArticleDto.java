package net.redciscso.javapmproject.dto;

import lombok.Data;

import java.util.List;

@Data
public class ArticleDto {
    private Long id;
    private String headerTitle;
    private List<ImageDto> images;
}
