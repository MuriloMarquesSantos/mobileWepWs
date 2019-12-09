package com.appsdeveloperblog.app.ws.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.appsdeveloperblog.app.ws.exceptions.UserServiceException;
import com.appsdeveloperblog.app.ws.io.entity.UserEntity;
import com.appsdeveloperblog.app.ws.io.repository.UserRepository;
import com.appsdeveloperblog.app.ws.service.UserService;
import com.appsdeveloperblog.app.ws.shared.Utils;
import com.appsdeveloperblog.app.ws.shared.dto.UserDTO;
import com.appsdeveloperblog.app.ws.ui.model.response.ErrorMessages;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final Utils utils;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, Utils utils, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.utils = utils;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDTO createUser(UserDTO user) {

        if (userRepository.findUserByEmail(user.getEmail()) != null)
            throw new RuntimeException("Record already exists");

        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);

        String publicUserId = utils.generateUserId(30);
        userEntity.setUserId(publicUserId);
        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        UserEntity storedUserDetails = userRepository.save(userEntity);

        UserDTO returnValue = new UserDTO();
        BeanUtils.copyProperties(storedUserDetails, returnValue);

        return returnValue;
    }

    @Override
    public UserDTO getUser(String email) {
        UserEntity userEntity = userRepository.findUserByEmail(email);

        if (userEntity == null) {
            throw new UsernameNotFoundException(email);
        }
        UserDTO returnValue = new UserDTO();
        BeanUtils.copyProperties(userEntity, returnValue);

        return returnValue;
    }

    @Override
    public UserDTO getUserByUserId(String id) {
        UserEntity userEntity = userRepository.findUserByUserId(id);

        if (userEntity == null) {
            throw new UsernameNotFoundException("User with id " + id + " not found.");
        }
        UserDTO returnValue = new UserDTO();
        BeanUtils.copyProperties(userEntity, returnValue);

        return returnValue;
    }

    @Override
    public UserDTO updateUser(String id, UserDTO userDto) {

        UserEntity userEntity = userRepository.findUserByUserId(id);

        if (userEntity == null) {
            throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        }

        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setLastName(userDto.getLastName());

        UserEntity storedUserDetails = userRepository.save(userEntity);

        UserDTO returnValue = new UserDTO();
        BeanUtils.copyProperties(storedUserDetails, returnValue);

        return returnValue;
    }

    @Override
    public void deleteUser(String id) {
        UserEntity userEntity = userRepository.findUserByUserId(id);

        if (userEntity == null) {
            throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        }

            userRepository.delete(userEntity);
    }

    @Override
    public List<UserDTO> getAllUsers(int page, int limit) {

        Pageable pageableRequest = PageRequest.of(page, limit);

        Page<UserEntity> usersPage = userRepository.findAll(pageableRequest);
        List<UserEntity> userEntities = usersPage.getContent();

        if (userEntities.isEmpty()) {
            throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        }

        List<UserDTO> returnValues = new ArrayList<>();
        userEntities.forEach(u ->  {
            UserDTO userDto = new UserDTO();
            BeanUtils.copyProperties(u, userDto);
            returnValues.add(userDto);
        });

        return returnValues;

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findUserByEmail(email);

        if (userEntity == null) {
            throw new UsernameNotFoundException(email);
        }

        return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());

    }
}
