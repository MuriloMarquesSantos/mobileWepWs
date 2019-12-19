package com.appsdeveloperblog.app.ws.service;

import com.appsdeveloperblog.app.ws.io.repository.AddressesRepository;
import com.appsdeveloperblog.app.ws.shared.dto.AddressDTO;
import com.appsdeveloperblog.app.ws.shared.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface AddressesService {
    List<AddressDTO> getAddressesByUserId(String userId);

}
