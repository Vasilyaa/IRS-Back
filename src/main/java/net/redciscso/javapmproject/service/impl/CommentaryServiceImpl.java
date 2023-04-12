package net.redciscso.javapmproject.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.redciscso.javapmproject.domain.Commentary;
import net.redciscso.javapmproject.domain.Header;
import net.redciscso.javapmproject.dto.CommentaryDto;
import net.redciscso.javapmproject.form.CommentaryForm;
import net.redciscso.javapmproject.mapper.CommentaryMapper;
import net.redciscso.javapmproject.repository.CommentaryRepository;
import net.redciscso.javapmproject.repository.HeaderRepository;
import net.redciscso.javapmproject.security.exception.NoHeaderException;
import net.redciscso.javapmproject.service.CommentaryService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@Primary
@RequiredArgsConstructor
public class CommentaryServiceImpl implements CommentaryService {
    private final HeaderRepository headerRepository;
    private final CommentaryRepository commentaryRepository;
    private final CommentaryMapper commentaryMapper;

    @Override
    public CommentaryDto add(CommentaryForm commentaryForm) {
        Commentary commentary = new Commentary();
        commentary.setContent(commentaryForm.getText());
        Optional<Header> header = headerRepository.findById(commentaryForm.getHeaderId());
        commentary.setHeader(header
                .orElseThrow(() -> new NoHeaderException("Header with id " + commentaryForm.getHeaderId() + "doesn't exist"))
        );
        Commentary newCommentary = commentaryRepository.save(commentary);

        return commentaryMapper.toDto(newCommentary);
    }

    @Override
    public void delete(Long id) {
        commentaryRepository.delete(commentaryRepository.getOne(id));
    }
}
