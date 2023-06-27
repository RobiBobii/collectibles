package com.collectibles.controller;

import com.collectibles.domain.User;
import com.collectibles.domain.dto.UserDto;
import com.collectibles.exceptions.UserNotFoundException;
import com.collectibles.mapper.UserMapper;
import com.collectibles.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(userMapper.mapToUserDtoList(users));
    }

    @GetMapping(value="{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) throws UserNotFoundException {
        return new ResponseEntity<>(userMapper.mapToUserDto(userService.getUser(id)), HttpStatus.OK);
    }

    @DeleteMapping(value="{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) throws UserNotFoundException {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        User savedUser = userService.saveUser(user);
        return ResponseEntity.ok(userMapper.mapToUserDto(savedUser));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createUser(@RequestBody UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        userService.saveUser(user);
        return ResponseEntity.ok().build();
    }
}