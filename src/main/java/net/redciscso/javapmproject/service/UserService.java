package net.redciscso.javapmproject.service;

import net.redciscso.javapmproject.domain.User;
import net.redciscso.javapmproject.domain.testing.Session;
import net.redciscso.javapmproject.dto.SessionInfo;
import net.redciscso.javapmproject.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto getUserInfo();

    SessionInfo getSessionInfoByTestId(Long testId);

    User getCurrentUser();

}
