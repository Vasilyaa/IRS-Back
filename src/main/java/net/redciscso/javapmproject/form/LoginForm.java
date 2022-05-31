package net.redciscso.javapmproject.form;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class LoginForm {

    @Size(max = 50,min = 5)
    private String username;
    @Size(max = 50,min = 6)
    private String password;

}

