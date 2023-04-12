package net.redciscso.javapmproject.service;

import net.redciscso.javapmproject.dto.CommentaryDto;
import net.redciscso.javapmproject.form.CommentaryForm;

public interface CommentaryService {
    CommentaryDto add(CommentaryForm commentaryForm);

    void delete(Long id);
}
