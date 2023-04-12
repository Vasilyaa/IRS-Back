package net.redciscso.javapmproject.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.redciscso.javapmproject.dto.TokenDto;
import net.redciscso.javapmproject.form.SignUpForm;
import net.redciscso.javapmproject.service.SignUpService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class SignUpController {

    public final static String signUpUri = "/signUp";

    private final SignUpService signUpService;

    @PostMapping(signUpUri)
    public TokenDto signUp(@RequestBody SignUpForm signUpForm) {
        return signUpService.signUp(signUpForm);
    }


}

