package net.redciscso.javapmproject.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.redciscso.javapmproject.domain.Role;
import net.redciscso.javapmproject.domain.Token;
import net.redciscso.javapmproject.domain.User;
import net.redciscso.javapmproject.domain.enums.RoleEnum;
import net.redciscso.javapmproject.dto.TokenDto;
import net.redciscso.javapmproject.exception.UserNameAlreadyExistException;
import net.redciscso.javapmproject.form.SignUpForm;
import net.redciscso.javapmproject.repository.RoleRepository;
import net.redciscso.javapmproject.repository.TokenRepository;
import net.redciscso.javapmproject.repository.UserRepository;
import net.redciscso.javapmproject.service.SignUpService;
import net.redciscso.javapmproject.util.StringGenerator;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SignUpServiceImpl implements SignUpService {

    private final TokenRepository tokenRepository;
    private final RoleRepository roleRepository;
    private final StringGenerator stringGenerator;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public TokenDto signUp(SignUpForm signUpForm) {

        //проверка на то не пытается ли человек ввести уже существующее имя пользователя
        checkDuplicateUsersByUsernameElseThrowException(signUpForm.getUsername());

        //создается новый пользоатель, пока как просто объект
        User newUser = createNewUserFromSignUpForm(signUpForm);

        //ему приставивается роль(по умолчания всегда Студент)
        setUserRoles(newUser);

        //юзер сохраняется в БД
        userRepository.save(newUser);

        //для нового юзера нужно сгенерировать токен
        Token newToken = Token.builder()
                .user(newUser)
                .value(stringGenerator.generateToken()).build();

        //и сохранить этот токен в БД
        tokenRepository.save(newToken);

        log.info("User signed up, user : [{}]", newUser);

        //возвращаем новый токен
        return new TokenDto(newToken.getValue());
    }

    private User createNewUserFromSignUpForm(SignUpForm signUpForm) {
        /*
            дздесь данные из формы подставляются в обхект юзерра, только пароль предварительно шифруется
         */
        return User.builder().
                username(signUpForm.getUsername()).
                fullName(signUpForm.getFullName()).
                password(stringGenerator.generatePasswordHash(signUpForm.getPassword())).
                build();
    }



    private void setUserRoles(User user) {
        List<Role> userRoles = new ArrayList<>();

        userRoles.add(roleRepository.findByName(RoleEnum.STUDENT));

        user.setRoles(userRoles);
    }

    private void checkDuplicateUsersByUsernameElseThrowException(String userName) {
        Optional<User> userByUsername = userRepository.findUserByUsername(userName);
        if (userByUsername.isPresent()) {
            throw new UserNameAlreadyExistException(userName);
        }
    }

}

