package com.collectibles.controller;

import com.collectibles.domain.User;
import com.collectibles.domain.dto.UserDto;
import com.collectibles.mapper.UserMapper;
import com.collectibles.service.UserService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private UserMapper userMapper;

    @Test
    void shouldGetUsers() throws Exception {
        //Given
        List<User> users = List.of(new User(1L, "name", "password", "role"));
        List<UserDto> userDtos = List.of(new UserDto(1L, "name", "password", "role"));

        when(userService.getAllUsers()).thenReturn(users);
        when(userMapper.mapToUserDtoList(users)).thenReturn(userDtos);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect((MockMvcResultMatchers.status()).is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("name")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].password", Matchers.is("password")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].role", Matchers.is("role")));
    }

    @Test
    void shouldGetUser() throws Exception {
        //Given
        User user = new User(1L, "name", "password", "role");
        UserDto userDto = new UserDto(1L, "name", "password", "role");

        when(userService.getUser(user.getId())).thenReturn(user);
        when(userMapper.mapToUserDto(user)).thenReturn(userDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/users/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect((MockMvcResultMatchers.status()).is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("name")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password", Matchers.is("password")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.role", Matchers.is("role")));
    }

    @Test
    void shouldDeleteUser() throws Exception {
        //Given
        doNothing().when(userService).deleteUser(anyLong());

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/v1/users/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void shouldUpdateUser()  throws Exception {
        //Given
        User user = new User(1L, "name", "password", "role");
        UserDto userDto = new UserDto(1L, "name", "password", "role");

        when(userMapper.mapToUser(any(UserDto.class))).thenReturn(user);
        when(userService.saveUser(user)).thenReturn(user);
        when(userMapper.mapToUserDto(user)).thenReturn(userDto);

        Gson gson = new Gson();
        String json = gson.toJson(userDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(json))
                .andExpect((MockMvcResultMatchers.status()).is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("name")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password", Matchers.is("password")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.role", Matchers.is("role")));
    }

    @Test
    void shouldCreateUser() throws Exception {
        //Given
        User user = new User(1L, "name", "password", "role");
        UserDto userDto = new UserDto(1L, "name", "password", "role");

        when(userMapper.mapToUser(userDto)).thenReturn(user);
        when(userService.saveUser(user)).thenReturn(user);

        Gson gson = new Gson();
        String json = gson.toJson(userDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(json))
                .andExpect((MockMvcResultMatchers.status()).is(200));

    }
}