package com.appsdeveloperblog.app.ws.service.impl;

import com.appsdeveloperblog.app.ws.io.entity.UserEntity;
import com.appsdeveloperblog.app.ws.shared.dto.AddressDTO;
import com.appsdeveloperblog.app.ws.shared.dto.UserDTO;

import java.util.Arrays;

public class UserServiceImplTestHelper {

    private UserServiceImplTestHelper() {
    }

    public static String USER_ID = "123123DFASFA2";

    public static String ADDRESS_ID = "asdasd123124";

    public static String ENCODED_PASSWORD = "DR#!@#$FAD";

    public static String EMAIL = "usr@gmai.com";

    public static String FIRST_NAME = "John";

    public static String LAST_NAME = "Johns";

    public static UserEntity createUserEntity() {
        return UserEntity.builder()
                .id(1L)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .email(EMAIL)
                .userId(USER_ID)
                .encryptedPassword(ENCODED_PASSWORD)
                .build();
    }

    public static UserDTO createUserDTOWithAddresses() {
        return UserDTO.builder()
                .addresses(Arrays.asList(
                        AddressDTO.builder()
                                .type("shipping")
                                .city("Vancouver")
                                .country("Canada")
                                .postalCode("ABC123")
                                .streetName("ABC")
                                .build(),
                        AddressDTO.builder()
                                .type("billing")
                                .city("Vancouver")
                                .country("Canada")
                                .postalCode("ABC123")
                                .streetName("ABC")
                                .build()
                        )
                ).build();
    }


}
