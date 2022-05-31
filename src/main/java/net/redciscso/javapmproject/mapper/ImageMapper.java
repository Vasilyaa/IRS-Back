package net.redciscso.javapmproject.mapper;

import net.redciscso.javapmproject.domain.Article;
import net.redciscso.javapmproject.domain.Image;
import net.redciscso.javapmproject.dto.ArticleDto;
import net.redciscso.javapmproject.dto.ImageDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ImageMapper {
    private final ModelMapper mapper;

    public ImageMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public ImageDto toDto(Image image){
        return mapper.map(image, ImageDto.class);
    }

    public List<ImageDto> listToDto(List<Image> imageList){
        List<ImageDto> imageDtoList = new ArrayList<>();
        for(Image image : imageList){
            imageDtoList.add(mapper.map(image, ImageDto.class));
        }
        return imageDtoList;
    }
}
