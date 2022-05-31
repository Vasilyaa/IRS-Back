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
    private static final String FILES_PATH = "C:\\Users\\User\\Desktop\\irs-images\\";
    private static final String IMAGE_FORMAT = "png";
    private final ImageRepository imageRepository;
    private final ArticleRepository articleRepository;
    private final ImageMapper imageMapper;

    @Override
    @SneakyThrows
    public ImageDto loadNew(ImageForm imageForm) {
        /*
            принцип сохранения изображений состоит в том, чтобы принять от клиента изображение в формате base64, декодировать его
            и сохранить в папке с  новым именем, которое представляет собой UUID(36 разрядный айди)
         */

        //в этой строчке избавляемся от лишней информации, полученной с клиента
        String base64 = imageForm.getImageBase64().split(",")[1];
        //создаем новый UUID
        UUID uuid = UUID.randomUUID();
        /*
            декодируем base64 в массив байтов
         */
        byte[] decodedBytes = Base64.getDecoder().decode(base64);
        //создаем новый путь для созранения туда изображения
        String imagePath = FILES_PATH + uuid + ".png";
        //записываем изображение по новому пути
        FileUtils.writeByteArrayToFile(new File(imagePath), decodedBytes);
        log.debug(imageForm.getImageBase64());

        //создаем  пустой объект изображения
        Image newImage = new Image();

        //из формы получаем артикл к которому принадлежит изображение
        Article article = articleRepository.getOne(imageForm.getArticleId());

        /*
             в новом изображении устанавиваем имя изображения и владеющий им артикл
         */
        newImage.setImagePath(uuid + ".png");
        newImage.setArticle(article);

        //возвращаем новое изображение в dto(в dto содержиться только имя изображения и айди артикла, к которому оно принадлежит)
        return imageMapper
                .toDto(imageRepository.save(newImage));
    }

    @Override
    public void delete(Long id) {
        imageRepository.delete(imageRepository.getOne(id));
    }

    @Override
    public void deleteAll() {
        imageRepository.deleteAll();
    }

    @Override
    @SneakyThrows
    public byte[] getImage(String name) {
        /*
            получаем мы изображение из той папки,  которую мы его и добавляли
         */
        BufferedImage image;
        image = ImageIO.read(new File(FILES_PATH + name));

        //неважно что это, важно что с помощью жтого мы можем из файла считать изображение
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(image, IMAGE_FORMAT, byteArrayOutputStream);

        //возвращаем изображение на клиент в виде массивва байтов(на фронте это все потом преобрпзовыается в обычное изображение)
        return byteArrayOutputStream.toByteArray();
    }
}
