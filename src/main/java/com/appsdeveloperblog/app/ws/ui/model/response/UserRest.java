package com.appsdeveloperblog.app.ws.ui.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRest {

    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private List<AddressesRest> addresses;
}
