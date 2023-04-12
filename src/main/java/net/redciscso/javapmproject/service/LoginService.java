package net.redciscso.javapmproject.service;

import net.redciscso.javapmproject.dto.TokenDto;
import net.redciscso.javapmproject.form.LoginForm;

/**
 * Implementation of this interface perform the login function in the system
 */
public interface LoginService {

    TokenDto login(LoginForm loginForm);

}

