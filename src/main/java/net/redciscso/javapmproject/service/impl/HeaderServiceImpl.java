package net.redciscso.javapmproject.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.redciscso.javapmproject.domain.Article;
import net.redciscso.javapmproject.domain.Commentary;
import net.redciscso.javapmproject.domain.Header;
import net.redciscso.javapmproject.dto.HeaderDto;
import net.redciscso.javapmproject.form.HeaderForm;
import net.redciscso.javapmproject.form.HeaderUpdateForm;
import net.redciscso.javapmproject.mapper.HeaderMapper;
import net.redciscso.javapmproject.repository.ArticleRepository;
import net.redciscso.javapmproject.repository.CommentaryRepository;
import net.redciscso.javapmproject.repository.HeaderRepository;
import net.redciscso.javapmproject.service.HeaderService;
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


    //метод возврата всех хедеров только в виде списка dto
    @Override
    public List<HeaderDto> getAllHeaders() {
        return headerMapper.listToDto(
                        headerRepository.findAllByOrderByCreateTime()
                );
    }


    //етод, возвращющий все хедеры, у которых в названии содержиться request
    @Override
    public List<HeaderDto> getAllContains(String request) {
        return headerMapper
                .listToDto(
                        headerRepository.findAllByNameContains(request)
                );
    }

    @Override
    public HeaderDto add(HeaderForm headerForm) {
        log.debug("Handled from client form: {}", headerForm);

        /*
            создаем пустой пустой объект хедера
            устанавливаем его имя, которое мы полуили из формы
         */
        Header header = new Header();
        header.setName(headerForm.getName());

        /*
            создаем пустой артикл
            в ранее созданый нами объект хедера устанавливаем соотверствующий ему артикл, который мы предварительно положили в бд
         */
        Article article = new Article();
        header.setArticle(articleRepository.save(article));

        /*
            сохраняем наш новый хедер в БД
         */
        Header newHeader = headerRepository.save(header);

        log.debug("New header: {}", newHeader);

        //возвращаем наш новый хедер, который с помощью маппера мы преобразовали из сущности в dto
        return headerMapper.toDto(
                        headerRepository.findById(newHeader.getId())
                        .orElseThrow(IllegalArgumentException::new)
                );
    }

    @Override
    public HeaderDto update(HeaderUpdateForm headerUpdateForm) {
        //для обновления нам нужно получить из формы айди изменяемого хедера и новое имя для этого хеддера

        //получаем по айди интересующий нас для изменений хедер
        Header newHeader = headerRepository.getOne(headerUpdateForm.getId());
        //устанавливаем в этот полученный объект новое имя из формы
        newHeader.setName(headerUpdateForm.getNewName());
        //сохраняем в БД и возвращаем в виде dto наш новый измененный хедер
        return headerMapper.toDto(headerRepository.save(newHeader));
    }

    @Override
    public void delete(Long id) {
        headerRepository.delete(headerRepository.getOne(id));
    }
}
