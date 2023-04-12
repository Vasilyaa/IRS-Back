package net.redciscso.javapmproject.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUpForm {

    @Size(max = 50,min = 5)
    private String username;
    @Size(max = 50,min = 5)
    private String fullName;
    @Size(max = 50,min = 6)
    private String password;

}

