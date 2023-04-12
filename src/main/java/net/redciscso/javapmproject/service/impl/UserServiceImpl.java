package net.redciscso.javapmproject.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.redciscso.javapmproject.domain.User;
import net.redciscso.javapmproject.domain.testing.Session;
import net.redciscso.javapmproject.dto.SessionInfo;
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

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Slf4j
@Service
@Primary
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    @Override
    public UserDto getUserInfo() {
        return userMapper
                .toDto(getCurrentUser());

    }

    @Override
    public SessionInfo getSessionInfoByTestId(Long testId) {
        SessionInfo sessionInfo = new SessionInfo();
        sessionInfo.setTryCount((int) getCurrentUser().getSessions().stream().filter(e -> Objects.equals(e.getTest().getId(), testId)).count());
        sessionInfo.setBestScores(getCurrentUser()
                .getSessions()
                .stream()
                .filter(e -> Objects.equals(e.getTest().getId(), testId))
                .mapToInt(Session::getCommonScores)
                .max()
                .orElse(0));
        Integer lastTryNum = getCurrentUser()
                .getSessions()
                .stream()
                .filter(e -> Objects.equals(e.getTest().getId(), testId))
                .mapToInt(Session::getTryNum)
                .max()
                .orElse(0);
        sessionInfo.setLastScores(Objects.requireNonNull(getCurrentUser().getSessions().stream().filter(e -> Objects.equals(e.getTryNum(), lastTryNum)).findFirst().orElse(null)).getCommonScores());
        return sessionInfo;
    }

    @Override
    public User getCurrentUser(){
        System.out.println(SecurityContextHolder.getContext().getAuthentication());
        return ((UserDetailsImpl)(SecurityContextHolder.getContext().getAuthentication().getDetails())).getUser();
    }
}
