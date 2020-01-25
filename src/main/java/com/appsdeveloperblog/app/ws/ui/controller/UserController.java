package com.appsdeveloperblog.app.ws.ui.controller;

import com.appsdeveloperblog.app.ws.service.AddressService;
import com.appsdeveloperblog.app.ws.service.UserService;
import com.appsdeveloperblog.app.ws.shared.dto.AddressDTO;
import com.appsdeveloperblog.app.ws.shared.dto.UserDTO;
import com.appsdeveloperblog.app.ws.ui.model.request.UserDetailsRequestModel;
import com.appsdeveloperblog.app.ws.ui.model.response.AddressesRest;
import com.appsdeveloperblog.app.ws.ui.model.response.UserRest;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    private UserService userService;
    private AddressService addressService;

    public UserController(UserService userService, AddressService addressService) {
        this.userService = userService;
        this.addressService = addressService;
    }

    @GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public UserRest getUser(@PathVariable String id) {

        UserDTO userDto = userService.getUserByUserId(id);

        UserRest returnValue = new ModelMapper().map(userDto, UserRest.class);

        return returnValue;
    }

    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<List<UserRest>> getAllUsers(@RequestParam(value = "page", defaultValue = "0") int page,
        @RequestParam(value = "limit", defaultValue = "25") int limit) {

        if (page > 0 ) {
            page = page -1;
        }

        List<UserRest> returnValues = new ArrayList<>();

        List<UserDTO> userDto = userService.getAllUsers(page, limit);

        ModelMapper modelMapper = new ModelMapper();

        userDto.forEach(u -> {
            UserRest userRest = modelMapper.map(u, UserRest.class);
            returnValues.add(userRest);
        });

        return ResponseEntity.ok()
                .body(returnValues);
    }

    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = {
        MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDetailsRequestModel userDetails) throws Exception {
        UserRest returnValue;

          //TO-DO Properly validate exception with ControllerAdvice
//        if (userDetails.getFirstName() == null || userDetails.getFirstName().isEmpty()) {
//            throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
//        }

        ModelMapper modelMapper = new ModelMapper();
        UserDTO userDto = modelMapper.map(userDetails, UserDTO.class);

        UserDTO createdUser = userService.createUser(userDto);

        returnValue = modelMapper.map(createdUser, UserRest.class);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(returnValue);
    }

    @PutMapping(path = "/{id}", consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
            MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public UserRest updateUser(@PathVariable String id, @RequestBody UserDetailsRequestModel userDetailsRequestModel) {

        ModelMapper modelMapper = new ModelMapper();

        UserDTO userDto = modelMapper.map(userDetailsRequestModel, UserDTO.class);

        UserDTO updatedUser = userService.updateUser(id, userDto);

        UserRest returnValue = modelMapper.map(updatedUser, UserRest.class);

        return returnValue;
    }

    @DeleteMapping(path = "/{id}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = "/{id}/addresses", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public List<AddressesRest> getUserAddresses(@PathVariable String id) {
        List<AddressDTO> addressDTOS = addressService.getAddressesByUserId(id);

        List<AddressesRest> returnValues = new ArrayList<>();

        addressDTOS.forEach(addressDTO -> returnValues.add(new ModelMapper().map(addressDTO, AddressesRest.class)));

        return returnValues;
    }

    @GetMapping(path = "/{id}/addresses/{addressId}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public AddressesRest getUserAddressById(@PathVariable String id, @PathVariable String addressId) {
        return new ModelMapper().map(addressService.getUserAddressById(addressId), AddressesRest.class);
    }

}
