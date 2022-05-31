package net.redciscso.javapmproject.service;

import net.redciscso.javapmproject.dto.TokenDto;
import net.redciscso.javapmproject.form.SignUpForm;

public interface SignUpService {
    TokenDto signUp(SignUpForm signUpForm);
}
