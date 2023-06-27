package com.collectibles.repository;

import com.collectibles.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private static final String NAME = "Test name";

    private User createTestUser() {
        return new User(NAME, "Test password", "Test role");
    }

    @Test
    void testUserRepositorySave() {
        //Given
        User user = createTestUser();

        //When
        userRepository.save(user);

        //Then
        Long id = user.getId();
        Optional<User> testUser = userRepository.findById(id);
        assertTrue(testUser.isPresent());

        //Cleanup
        userRepository.deleteById(id);
    }

    @Test
    void testUserRepositoryFindByName() {
        //Given
        User user = createTestUser();

        //When
        userRepository.save(user);
        Long id = user.getId();
        List<User> testUsers = userRepository.findByName(NAME);

        //Then
        assertNotNull(testUsers);

        //Cleanup
        userRepository.deleteById(id);
    }
}