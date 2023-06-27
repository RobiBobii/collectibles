package com.collectibles.mapper;

import com.collectibles.domain.User;
import com.collectibles.domain.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserMapper {

    public User mapToUser(final UserDto userDto) {
        return new User(
                userDto.getId(),
                userDto.getName(),
                userDto.getPassword(),
                userDto.getRole()
        );
    }

    public UserDto mapToUserDto(final User user) {
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getPassword(),
                user.getRole()
        );
    }

    public List<UserDto> mapToUserDtoList(final List<User> userList) {
        return userList.stream()
                .map(this::mapToUserDto)
                .collect(Collectors.toList());
    }
}