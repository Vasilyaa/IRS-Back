package net.redciscso.javapmproject.service;

import net.redciscso.javapmproject.dto.ArticleDto;

public interface ArticleService {
    ArticleDto getById(Long id);
}
