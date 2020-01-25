package com.appsdeveloperblog.app.ws.service.impl;

import com.appsdeveloperblog.app.ws.io.entity.AddressEntity;
import com.appsdeveloperblog.app.ws.io.entity.UserEntity;
import com.appsdeveloperblog.app.ws.io.repository.AddressRepository;
import com.appsdeveloperblog.app.ws.io.repository.UserRepository;
import com.appsdeveloperblog.app.ws.service.AddressService;
import com.appsdeveloperblog.app.ws.shared.dto.AddressDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    private final UserRepository userRepository;

    private final AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(UserRepository userRepository, AddressRepository addressRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public List<AddressDTO> getAddressesByUserId(String userId) {
        UserEntity userEntity = userRepository.findUserByUserId(userId);

        if (userEntity == null) {
            throw  new UsernameNotFoundException("User not Found");
        }

        List<AddressEntity> addressEntities = addressRepository.findAllByUserDetails(userEntity);
        List<AddressDTO> returnValues = new ArrayList<>();

        if(addressEntities.isEmpty() || addressEntities == null) {
            throw new RuntimeException("No Addresses registered for this user");
        }
        addressEntities.forEach(addressEntity -> returnValues.add(new ModelMapper().map(addressEntity, AddressDTO.class)));

        return returnValues;
    }

    @Override
    public AddressDTO getUserAddressById(String addressId) {
        return new ModelMapper().map(addressRepository.findByAddressId(addressId), AddressDTO.class);
    }
}
