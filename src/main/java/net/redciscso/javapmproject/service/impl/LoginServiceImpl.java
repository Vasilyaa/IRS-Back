package net.redciscso.javapmproject.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.redciscso.javapmproject.domain.Role;
import net.redciscso.javapmproject.domain.Token;
import net.redciscso.javapmproject.domain.User;
import net.redciscso.javapmproject.dto.TokenDto;
import net.redciscso.javapmproject.exception.UserNotFoundException;
import net.redciscso.javapmproject.form.LoginForm;
import net.redciscso.javapmproject.repository.TokenRepository;
import net.redciscso.javapmproject.repository.UserRepository;
import net.redciscso.javapmproject.service.LoginService;
import net.redciscso.javapmproject.util.StringGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginServiceImpl implements LoginService {

    @Value("${token.length}")
    private long tokenLength;

    private final UserRepository userRepository;

    private final TokenRepository tokenRepository;

    private final PasswordEncoder passwordEncoder;

    private final StringGenerator stringGenerator;

    @Override
    public TokenDto login(LoginForm loginForm) {
        //тут мы хотим проерить есть ли в бд пользователь с даннными из формы или нет
        User user = getUser(loginForm);

        //если есть, то ему над о сгенерировать токен с помощью StringGenerator
        Token newToken = Token.builder()
                .user(user)
                .value(stringGenerator.generateToken()).build();

        //сохраняем новый токен
        tokenRepository.save(newToken);

        log.info("User successfully logged in, user.id : [{}]", user.getId());

        //возвращаем пользователю его новый токен
        return new TokenDto(newToken.getValue());
    }

    private User getUser(LoginForm loginForm) {
        /*
            если вкарце, то в этом методе происходит проверка того есть ли пользователь с такими дданными в Бд, если нет, то выбрасыается ошибка
         */
        String login = loginForm.getUsername();
        Optional<User> byLogin = userRepository.findByUsername(login);
        if (byLogin.isEmpty() || !passwordEncoder
                .matches(loginForm.getPassword(), byLogin.get().getPassword())) {
            throw new UserNotFoundException(login);
        }
        return byLogin.get();
    }

}

