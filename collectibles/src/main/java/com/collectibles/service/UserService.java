package com.collectibles.service;

import com.collectibles.domain.User;
import com.collectibles.exceptions.UserNotFoundException;
import com.collectibles.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUser(final Long id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    public User saveUser(final User user) {
        return userRepository.save(user);
    }

    public void deleteUser(final Long id) throws UserNotFoundException {
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            throw new UserNotFoundException();
        }
    }
}