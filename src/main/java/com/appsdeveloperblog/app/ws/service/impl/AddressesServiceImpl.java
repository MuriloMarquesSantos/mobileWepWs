package com.appsdeveloperblog.app.ws.service.impl;

import com.appsdeveloperblog.app.ws.io.entity.AddressEntity;
import com.appsdeveloperblog.app.ws.io.entity.UserEntity;
import com.appsdeveloperblog.app.ws.io.repository.AddressesRepository;
import com.appsdeveloperblog.app.ws.io.repository.UserRepository;
import com.appsdeveloperblog.app.ws.service.AddressesService;
import com.appsdeveloperblog.app.ws.shared.dto.AddressDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressesServiceImpl implements AddressesService {

    private final UserRepository userRepository;

    private final AddressesRepository addressesRepository;

    @Autowired
    public AddressesServiceImpl(UserRepository userRepository, AddressesRepository addressesRepository) {
        this.userRepository = userRepository;
        this.addressesRepository = addressesRepository;
    }

    @Override
    public List<AddressDTO> getAddressesByUserId(String userId) {
        UserEntity userEntity = userRepository.findUserByUserId(userId);

        if (userEntity == null) {
            throw  new UsernameNotFoundException("User not Found");
        }

        List<AddressEntity> addressEntities = addressesRepository.findAllByUserDetails(userEntity);
        List<AddressDTO> returnValues = new ArrayList<>();

        if(addressEntities.isEmpty() || addressEntities == null) {
            throw new RuntimeException("No Addresses registered for this user");
        }
        addressEntities.forEach(addressEntity -> returnValues.add(new ModelMapper().map(addressEntity, AddressDTO.class)));

        return returnValues;
    }
}
