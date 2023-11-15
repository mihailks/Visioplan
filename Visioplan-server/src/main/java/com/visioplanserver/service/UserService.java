package com.visioplanserver.service;

import com.visioplanserver.model.dto.UserProfileEditDTO;
import com.visioplanserver.model.dto.UserRegistrationDTO;
import com.visioplanserver.model.entity.UserEntity;
import com.visioplanserver.model.view.UserViewModel;
import com.visioplanserver.model.view.UserWithRoleViewModel;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface UserService {
    void registerUser(UserRegistrationDTO userRegistrationDTO);

    UserViewModel findUserByUsername(String name);

    void updateUserProfile(UserProfileEditDTO userProfileEditDTO);

    void deleteUser(Long id);

    Page<UserWithRoleViewModel> findPage(int pageNumber);


    void promoteUser(Long id);

    void demoteUser(Long id);

    UserWithRoleViewModel findUserRoleByUsername(String name);

    UserEntity findUserByUsernameEntity(String username);
}


