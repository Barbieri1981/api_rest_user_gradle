package com.api.rest.service;
import com.api.rest.dto.request.UserRqDTO;
import com.api.rest.dto.response.UserRsDTO;

public interface UserService {

    UserRsDTO createUser(UserRqDTO user);
}
