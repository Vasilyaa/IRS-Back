package net.redciscso.javapmproject.controllers;


import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.redciscso.javapmproject.dto.SessionInfo;
import net.redciscso.javapmproject.dto.UserDto;
import net.redciscso.javapmproject.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(UserController.USER_ENDPOINT)
public class UserController {
    private final UserService userService;

    public static final String USER_ENDPOINT = "/user";

    public static final String USER_INFO_ENDPOINT = "/info";

    public static final String USER_SESSION_INFO_ENDPOINT = "/session/info/{testId}";


    @CrossOrigin
    @GetMapping(USER_INFO_ENDPOINT)
    public UserDto getUserInfo(){
        return userService.getUserInfo();
    }


    @CrossOrigin
    @GetMapping(USER_SESSION_INFO_ENDPOINT)
    public SessionInfo getSessionInfoByTestId(@PathVariable Long testId){
        return userService.getSessionInfoByTestId(testId);
    }

}
