package com.collectibles.mapper;

import com.collectibles.domain.User;
import com.collectibles.domain.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void shouldMapToUser() {
        //Given
        UserDto userDto = new UserDto(1L, "name", "password", "role");

        //When
        User user = userMapper.mapToUser(userDto);

        //Then
        assertEquals(userDto.getId(), user.getId());
        assertEquals(userDto.getName(), user.getName());
        assertEquals(userDto.getPassword(), user.getPassword());
        assertEquals(userDto.getRole(), user.getRole());
    }

    @Test
    void shouldMapToUserDto() {
        //Given
        User user = new User(1L, "name", "password", "role");

        //When
        UserDto userDto = userMapper.mapToUserDto(user);

        //Then
        assertEquals(user.getId(), userDto.getId());
        assertEquals(user.getName(), userDto.getName());
        assertEquals(user.getPassword(), userDto.getPassword());
        assertEquals(user.getRole(), userDto.getRole());
    }

    @Test
    void shouldMapToUserDtoList() {
        //Given
        User user = new User(1L, "name", "password", "role");
        List<User> users = List.of(user);

        //When
        List<UserDto> userDtos = userMapper.mapToUserDtoList(users);

        //Then
        assertEquals(users.size(), userDtos.size());
        assertEquals(users.get(0).getId(), userDtos.get(0).getId());
        assertEquals(users.get(0).getName(), userDtos.get(0).getName());
        assertEquals(users.get(0).getPassword(), userDtos.get(0).getPassword());
        assertEquals(users.get(0).getRole(), userDtos.get(0).getRole());
    }
}