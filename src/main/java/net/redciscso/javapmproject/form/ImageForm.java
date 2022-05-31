package net.redciscso.javapmproject.form;

import lombok.Data;

import java.util.Base64;

@Data
public class ImageForm {
    private Long articleId;
    private String imageBase64;
}
