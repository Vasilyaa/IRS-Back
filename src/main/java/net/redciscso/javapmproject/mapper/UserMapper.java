package net.redciscso.javapmproject.mapper;

import net.redciscso.javapmproject.domain.Article;
import net.redciscso.javapmproject.domain.User;
import net.redciscso.javapmproject.dto.HeaderDto;
import net.redciscso.javapmproject.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    private final ModelMapper mapper;

    public UserMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public UserDto toDto(User user){
        UserDto userDto = mapper.map(user, UserDto.class);
        userDto.setRoleName(
                user.getRoles()
                        .stream()
                        .map(e -> e.getName().getDescription())
                        .collect(Collectors.joining(","))
        );
        return userDto;
    }

    public List<UserDto> listToDto(List<User> userList){
        List<UserDto> userDtoList = new ArrayList<>();
        for(User user : userList){
            userDtoList.add(mapper.map(user, UserDto.class));
        }
        return userDtoList;
    }
}
