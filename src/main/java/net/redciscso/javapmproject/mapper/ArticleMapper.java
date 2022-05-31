package net.redciscso.javapmproject.mapper;

import net.redciscso.javapmproject.domain.Article;
import net.redciscso.javapmproject.dto.ArticleDto;
import net.redciscso.javapmproject.dto.HeaderDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ArticleMapper {
    private final ModelMapper mapper;

    public ArticleMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public ArticleDto toDto(Article article){
        ArticleDto articleDto = mapper.map(article, ArticleDto.class);
        articleDto.setHeaderTitle(article.getHeader().getName());
        return articleDto;
    }

    public List<ArticleDto> listToDto(List<Article> articleList){
        List<ArticleDto> articleDtoList = new ArrayList<>();
        for(Article article : articleList){
            articleDtoList.add(mapper.map(article, ArticleDto.class));
        }
        return articleDtoList;
    }
}
