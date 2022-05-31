package net.redciscso.javapmproject.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.redciscso.javapmproject.domain.User;
import net.redciscso.javapmproject.dto.UserDto;
import net.redciscso.javapmproject.exception.UserNotFoundException;
import net.redciscso.javapmproject.mapper.UserMapper;
import net.redciscso.javapmproject.repository.UserRepository;
import net.redciscso.javapmproject.security.details.UserDetailsImpl;
import net.redciscso.javapmproject.service.UserService;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@Primary
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    @Override
    public UserDto getUserInfo() {
        //возвращаем информацию о текущем пользователе
        return userMapper
                .toDto(getCurrentUser());

    }

    /*
        здесь мы получаем информацию о текущем зарегистрированном в системе ползоватете
     */
    public User getCurrentUser(){
        System.out.println(SecurityContextHolder.getContext().getAuthentication());
        return ((UserDetailsImpl)(SecurityContextHolder.getContext().getAuthentication().getDetails())).getUser();
    }
}
