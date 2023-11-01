package com.visioplanserver.service.impl;

import com.visioplanserver.model.dto.UserRegistrationDTO;
import com.visioplanserver.model.entity.UserEntity;
import com.visioplanserver.model.view.UserViewModel;
import com.visioplanserver.repository.UserRepository;
import com.visioplanserver.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void registerUser(UserRegistrationDTO userRegistrationDTO) {
        UserEntity user = modelMapper.map(userRegistrationDTO, UserEntity.class);
        user.setPassword(passwordEncoder.encode(userRegistrationDTO.password()));
        userRepository.save(user);
    }

    @Override
    public UserViewModel findUserByUsername(String name) {
        UserEntity userEntity = userRepository.findByUsername(name).orElse(null);
        if (userEntity!=null){
            String companyName = userEntity.getCompany().getName();
            UserViewModel userViewModel = modelMapper.map(userEntity, UserViewModel.class);
            userViewModel.setCompany(companyName);
            return userViewModel;
        }
        return null;
    }


}
