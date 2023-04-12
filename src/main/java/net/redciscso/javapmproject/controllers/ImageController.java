package net.redciscso.javapmproject.controllers;

import lombok.RequiredArgsConstructor;
import net.redciscso.javapmproject.dto.ImageDto;
import net.redciscso.javapmproject.form.ImageForm;
import net.redciscso.javapmproject.service.ImageService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ImageController.IMAGE_URI)
@RequiredArgsConstructor
public class ImageController {
    public static final String IMAGE_URI = "/image";
    public static final String ADD_IMAGE_URI = "/add";
    public static final String DELETE_IMAGE_URI = "/delete/{id}";
    public static final String DELETE_ALL_IMAGE_URI = "/deleteAll/{articleId}";
    public static final String GET_IMAGE_URI = "/get/{name}";

    private final ImageService imageService;

    @CrossOrigin
    @PostMapping(ADD_IMAGE_URI)
    public ImageDto loadNew(@RequestBody ImageForm imageForm){
        return imageService.loadNew(imageForm);
    }
    @CrossOrigin
    @GetMapping(value = GET_IMAGE_URI, produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getImage(@PathVariable String name){
        return imageService.getImage(name);
    }

    @CrossOrigin
    @GetMapping(DELETE_IMAGE_URI)
    public void delete(@PathVariable Long id){
        imageService.delete(id);
    }

    @CrossOrigin
    @GetMapping(DELETE_ALL_IMAGE_URI)
    public void deleteAll(@PathVariable Long articleId){
        imageService.deleteAll(articleId);
    }
}
