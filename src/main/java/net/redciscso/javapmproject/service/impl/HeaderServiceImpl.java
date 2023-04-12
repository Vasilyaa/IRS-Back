package net.redciscso.javapmproject.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.redciscso.javapmproject.domain.Article;
import net.redciscso.javapmproject.domain.Commentary;
import net.redciscso.javapmproject.domain.Header;
import net.redciscso.javapmproject.domain.Subject;
import net.redciscso.javapmproject.dto.HeaderDto;
import net.redciscso.javapmproject.form.HeaderForm;
import net.redciscso.javapmproject.form.HeaderUpdateForm;
import net.redciscso.javapmproject.mapper.HeaderMapper;
import net.redciscso.javapmproject.repository.ArticleRepository;
import net.redciscso.javapmproject.repository.CommentaryRepository;
import net.redciscso.javapmproject.repository.HeaderRepository;
import net.redciscso.javapmproject.repository.SubjectRepository;
import net.redciscso.javapmproject.service.HeaderService;
import net.redciscso.javapmproject.service.SubjectService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@Primary
@RequiredArgsConstructor
public class HeaderServiceImpl implements HeaderService {
    private final HeaderRepository headerRepository;
    private final HeaderMapper headerMapper;
    private final ArticleRepository articleRepository;

    private final SubjectRepository subjectRepository;

    @Override
    public List<HeaderDto> getAllHeaders() {
        return headerMapper.listToDto(
                        headerRepository.findAllByOrderByCreateTime()
                );
    }

    @Override
    public List<HeaderDto> getAllBySubjectId(Long subjectId) {
        return headerMapper.listToDto(
                headerRepository.findAllBySubjectId(subjectId)
        );
    }

    @Override
    public List<HeaderDto> getAllContains(Long subjectId, String request) {
        return headerMapper
                .listToDto(
                        headerRepository.findAllByNameContainsAndSubjectId(request, subjectId)
                );
    }

    @Override
    public HeaderDto add(HeaderForm headerForm) {
        log.debug("Handled from client form: {}", headerForm);

        Header header = new Header();
        header.setName(headerForm.getName());

        Subject subject = subjectRepository.getOne(headerForm.getSubjectId());
        header.setSubject(subject);

        Article article = new Article();
        header.setArticle(articleRepository.save(article));

        Header newHeader = headerRepository.save(header);

        log.debug("New header: {}", newHeader);

        return headerMapper
                .toDto(
                        headerRepository.findById(newHeader.getId())
                        .orElseThrow(IllegalArgumentException::new)
                );
    }

    @Override
    public HeaderDto update(HeaderUpdateForm headerUpdateForm) {
        Header newHeader = headerRepository.getOne(headerUpdateForm.getId());
        newHeader.setName(headerUpdateForm.getNewName());
        return headerMapper.toDto(headerRepository.save(newHeader));
    }

    @Override
    public void delete(Long id) {
        headerRepository.delete(headerRepository.getOne(id));
    }
}
