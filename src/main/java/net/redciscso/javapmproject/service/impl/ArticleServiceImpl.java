package net.redciscso.javapmproject.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.redciscso.javapmproject.dto.ArticleDto;
import net.redciscso.javapmproject.mapper.ArticleMapper;
import net.redciscso.javapmproject.repository.ArticleRepository;
import net.redciscso.javapmproject.service.ArticleService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Slf4j
@Service
//против коллизии бинов
@Primary
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;

    @Override
    public ArticleDto getById(Long id) {

        return articleMapper
                .toDto(articleRepository.getOne(id));
    }
}
