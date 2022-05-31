package net.redciscso.javapmproject.service;

import net.redciscso.javapmproject.dto.ImageDto;
import net.redciscso.javapmproject.form.ImageForm;

public interface ImageService {
    ImageDto loadNew(ImageForm imageForm);

    void delete(Long id);

    void deleteAll();

    byte[] getImage(String name);
}
