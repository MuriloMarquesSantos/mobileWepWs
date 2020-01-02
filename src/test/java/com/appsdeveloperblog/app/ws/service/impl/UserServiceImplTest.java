package com.appsdeveloperblog.app.ws.service.impl;

import com.appsdeveloperblog.app.ws.io.entity.UserEntity;
import com.appsdeveloperblog.app.ws.io.repository.UserRepository;
import com.appsdeveloperblog.app.ws.shared.dto.UserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getUserWithValidEmail_then_shouldReturnValidEntity() {
        UserEntity userEntity = UserEntity.builder()
                .email("user@gmail.com")
                .build();

        Mockito.when(userRepository.findUserByEmail(Mockito.anyString())).thenReturn(userEntity);

        UserDTO returnedValue = userServiceImpl.getUser("usr@gmai.com");

        assertNotNull(returnedValue);
    }

    @Test
    public void getUserWithInValidEmail_then_shouldThrowUsernameNotFoundException() {
        Mockito.when(userRepository.findUserByEmail(Mockito.anyString())).thenReturn(null);
        assertThrows(UsernameNotFoundException.class, () -> userServiceImpl.getUser("user@gmai.com"));
    }
}