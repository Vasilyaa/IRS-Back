package net.redciscso.javapmproject.dto;

import lombok.Data;

import java.util.List;

@Data
public class SubjectDto {
    private Long id;
    private String name;
    private Integer semester;
    private String icon;
}
