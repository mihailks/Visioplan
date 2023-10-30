package com.visioplanserver.service;

import com.visioplanserver.model.dto.UserRegistrationDTO;

public interface UserService {
    void registerUser(UserRegistrationDTO userRegistrationDTO);
}
