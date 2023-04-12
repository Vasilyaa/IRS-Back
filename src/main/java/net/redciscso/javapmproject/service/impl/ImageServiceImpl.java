package net.redciscso.javapmproject.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.redciscso.javapmproject.domain.Article;
import net.redciscso.javapmproject.domain.Image;
import net.redciscso.javapmproject.dto.ImageDto;
import net.redciscso.javapmproject.form.ImageForm;
import net.redciscso.javapmproject.mapper.ImageMapper;
import net.redciscso.javapmproject.repository.ArticleRepository;
import net.redciscso.javapmproject.repository.HeaderRepository;
import net.redciscso.javapmproject.repository.ImageRepository;
import net.redciscso.javapmproject.service.ImageService;
import org.apache.commons.io.FileUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Base64;
import java.util.UUID;

@Slf4j
@Service
@Primary
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    private static final String FILES_PATH = "C:\\Users\\Sergey\\Desktop\\irs-images\\";
    private static final String IMAGE_FORMAT = "png";
    private final ImageRepository imageRepository;
    private final ArticleRepository articleRepository;
    private final ImageMapper imageMapper;

    @Override
    @SneakyThrows
    public ImageDto loadNew(ImageForm imageForm) {
        String base64 = imageForm.getImageBase64().split(",")[1];
        UUID uuid = UUID.randomUUID();
        byte[] decodedBytes = Base64.getDecoder().decode(base64);
        String imagePath = FILES_PATH + uuid + ".png";
        FileUtils.writeByteArrayToFile(new File(imagePath), decodedBytes);
        log.debug(imageForm.getImageBase64());

        Image newImage = new Image();

        Article article = articleRepository.getOne(imageForm.getArticleId());

        newImage.setImagePath(uuid + ".png");
        newImage.setArticle(article);

        return imageMapper
                .toDto(imageRepository.save(newImage));
    }

    @Override
    public void delete(Long id) {
        imageRepository.delete(imageRepository.getOne(id));
    }

    @Override
    @Transactional
    public void deleteAll(Long articleId) {
        log.debug("articleId: {}", articleId);
        imageRepository.deleteAllByArticleId(articleId);
    }

    @Override
    @SneakyThrows
    public byte[] getImage(String name) {
        BufferedImage image;
        image = ImageIO.read(new File(FILES_PATH + name));

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(image, IMAGE_FORMAT, byteArrayOutputStream);

        return byteArrayOutputStream.toByteArray();
    }
}
