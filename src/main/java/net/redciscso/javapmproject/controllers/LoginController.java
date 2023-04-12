package net.redciscso.javapmproject.controllers;

import lombok.RequiredArgsConstructor;
import net.redciscso.javapmproject.dto.TokenDto;
import net.redciscso.javapmproject.form.LoginForm;
import net.redciscso.javapmproject.service.LoginService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
//объявляю класс контролером
@RestController
//создаем конструкторыс любыми парамтерами
@RequiredArgsConstructor

public class LoginController {
//в это поле инжектится бин логин сервиса
    private final LoginService loginService;

    public static final String loginUri = "/login";

    //обход гуглв
    @CrossOrigin
    //здесь обрабатываются только пост запросы
    @PostMapping(loginUri)
    //заппрос возвращает токен
    //@RequestBody объявляет что дальше следует тело запроса
    public TokenDto login(@RequestBody LoginForm loginForm){
        return loginService.login(loginForm);
    }


}

