package com.example.merchstore;

import com.example.merchstore.DAO.models.User;
import com.example.merchstore.DAO.models.enums.Role;
import com.example.merchstore.repositories.UserRepository;
import com.example.merchstore.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceTest {

    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        userService = new UserService(userRepository, passwordEncoder);
    }

    @Test
    void testCreateUser() {
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password");
        when(userRepository.findByEmail(any())).thenReturn(null);
        when(passwordEncoder.encode(any())).thenReturn("encodedPassword");
        assertTrue(userService.createUser(user));
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testCreateUserWithExistingEmail() {
        User user = new User();
        user.setEmail("test@example.com");
        when(userRepository.findByEmail(any())).thenReturn(new User());
        assertFalse(userService.createUser(user));
        verify(userRepository, never()).save(any());
    }

    @Test
    void testList() {
        List<User> userList = new ArrayList<>();
        User user1 = new User();
        user1.setId(1L);
        user1.setEmail("test1@example.com");
        User user2 = new User();
        user2.setId(2L);
        user2.setEmail("test2@example.com");
        userList.add(user1);
        userList.add(user2);
        when(userRepository.findAll()).thenReturn(userList);
        assertEquals(userList, userService.list());
    }

    @Test
    void testBanUser() {
        User user = new User();
        user.setId(1L);
        user.setEmail("test@example.com");
        user.setActive(true);
        when(userRepository.findById(any())).thenReturn(Optional.of(user));
        userService.banUser(1L);
        assertFalse(user.isActive());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testUnbanUser() {
        User user = new User();
        user.setId(1L);
        user.setEmail("test@example.com");
        user.setActive(false);
        when(userRepository.findById(any())).thenReturn(Optional.of(user));
        userService.banUser(1L);
        assertTrue(user.isActive());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testChangeUserRoles() {
        User user = new User();
        user.setId(1L);
        user.setEmail("test@example.com");
        Map<String, String> form = new HashMap<>();
        form.put(Role.ROLE_USER.name(), "on");
        form.put(Role.ROLE_ADMIN.name(), "on");
        userService.changeUserRoles(user, form);
        Set<Role> expectedRoles = new HashSet<>(Arrays.asList(Role.ROLE_USER, Role.ROLE_ADMIN));
        assertEquals(expectedRoles, user.getRoles());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testGetUserByPrincipal() {
        User user = new User();
        user.setId(1L);
        user.setEmail("test@example.com");
//        when(userRepository.findByEmail(any())).thenReturn(user);
//        assertEquals(user, userService.getUserByPrincipal(null));
    }
}