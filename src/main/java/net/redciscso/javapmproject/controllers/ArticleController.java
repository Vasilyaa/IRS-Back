package net.redciscso.javapmproject.controllers;

import lombok.RequiredArgsConstructor;
import net.redciscso.javapmproject.dto.ArticleDto;
import net.redciscso.javapmproject.dto.ImageDto;
import net.redciscso.javapmproject.form.ImageForm;
import net.redciscso.javapmproject.service.ArticleService;
import net.redciscso.javapmproject.service.ImageService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ArticleController.ARTICLE_URI)
@RequiredArgsConstructor
public class ArticleController {
    public static final String ARTICLE_URI = "/article";
    public static final String GET_ARTICLE_URI = "/get/{id}";

    private final ArticleService articleService;

    @CrossOrigin
    @GetMapping(GET_ARTICLE_URI)
    public ArticleDto getById(@PathVariable Long id){
        return articleService.getById(id);
    }

}
