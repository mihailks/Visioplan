package com.visioplanserver.service;

import com.visioplanserver.model.dto.UserRegistrationDTO;
import com.visioplanserver.model.entity.UserEntity;
import com.visioplanserver.model.view.UserViewModel;

import java.util.Optional;

public interface UserService {
    void registerUser(UserRegistrationDTO userRegistrationDTO);

    UserViewModel findUserByUsername(String name);
}


