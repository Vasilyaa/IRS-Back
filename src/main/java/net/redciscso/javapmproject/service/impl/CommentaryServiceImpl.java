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

    //метод добавления нового коммента по анным из формы, метод возвращает dto
    @Override
    public CommentaryDto add(CommentaryForm commentaryForm) {

        //создается пустой объект класса Commentary
        Commentary commentary = new Commentary();
        //у этого класса есть поле content то есть сам тект коммментария, его мы берем из commentaryForm
        commentary.setContent(commentaryForm.getText());
        //с помощью объекта доступа к БД репозитория для хедеров мы по айдишнику хедера получаем хедер, которому принадлежит наш моздаваемый комментарий
        Optional<Header> header = headerRepository.findById(commentaryForm.getHeaderId());
        //теперь когда нам известен хедер, которому принажделит комментарий  мы можем добавить этот хедер в наш объект комментария
        //если хедер не найден выбрамываем ошибку с указанием на то, что хедера с таким айди не существует
        commentary.setHeader(header
                .orElseThrow(() -> new NoHeaderException("Header with id " + commentaryForm.getHeaderId() + "doesn't exist"))
        );

        //благодаря тому, что метод репозитория save возвращщает только что добавленный в БД объект мы можеи получить наш новй комментарий для его последующего
        //возврата в сервисный слой, откуда он вернется на клиент, с которого был послан запрос на добавление нового комментария
        Commentary newCommentary = commentaryRepository.save(commentary);

        return commentaryMapper.toDto(newCommentary);
    }

    @Override
    public void delete(Long id) {
        commentaryRepository.delete(commentaryRepository.getOne(id));
    }
}
