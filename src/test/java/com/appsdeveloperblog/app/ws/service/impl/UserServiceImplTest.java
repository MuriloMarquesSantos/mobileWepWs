package com.appsdeveloperblog.app.ws.service.impl;

import com.appsdeveloperblog.app.ws.exceptions.UserServiceException;
import com.appsdeveloperblog.app.ws.io.entity.UserEntity;
import com.appsdeveloperblog.app.ws.io.repository.UserRepository;
import com.appsdeveloperblog.app.ws.shared.Utils;
import com.appsdeveloperblog.app.ws.shared.dto.UserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static com.appsdeveloperblog.app.ws.service.impl.UserServiceImplTestHelper.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private Utils utils;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    private UserEntity userEntity;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        userEntity = UserServiceImplTestHelper.createUserEntity();
    }

    @Test
    public void getUserWithValidEmail_then_shouldReturnValidEntity() {
        when(userRepository.findUserByEmail(anyString())).thenReturn(userEntity);

        UserDTO returnedValue = userServiceImpl.getUser(EMAIL);

        assertNotNull(returnedValue);
    }

    @Test
    public void getUserWithInValidEmail_then_shouldThrowUsernameNotFoundException() {
        when(userRepository.findUserByEmail(anyString())).thenReturn(null);
        assertThrows(UsernameNotFoundException.class, () -> userServiceImpl.getUser(EMAIL));
    }

    @Test
    public void createUserWithValidValidAttributes_then_ShouldReturnValidUserDTO() {
        when(userRepository.findUserByEmail(anyString())).thenReturn(null);
        when(utils.generateAddressId(anyInt())).thenReturn(ADDRESS_ID);
        when(utils.generateUserId(anyInt())).thenReturn(USER_ID);
        when(bCryptPasswordEncoder.encode(anyString())).thenReturn(ENCODED_PASSWORD);
        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);

        UserDTO storedDetails = userServiceImpl.createUser(UserServiceImplTestHelper.createUserDTOWithAddresses());

        assertNotNull(storedDetails);
        assertEquals(userEntity.getFirstName(), storedDetails.getFirstName());
        assertEquals(userEntity.getLastName(), storedDetails.getLastName());
        assertNotNull(storedDetails.getUserId());
        assertEquals(storedDetails.getAddresses(), userEntity.getAddresses());
    }

    @Test
    public void createUserWithExistingEmail_then_ShouldThrownServiceException() {
        when(userRepository.findUserByEmail(anyString())).thenReturn(userEntity);

        UserDTO userDTO = UserServiceImplTestHelper.createUserDTOWithAddresses();

        assertThrows(UserServiceException.class, () -> userServiceImpl.createUser(userDTO));
    }
}