package com.visioplanserver.service.impl;

import com.visioplanserver.model.dto.UserProfileEditDTO;
import com.visioplanserver.model.dto.UserRegistrationDTO;
import com.visioplanserver.model.entity.UserEntity;
import com.visioplanserver.model.view.UserViewModel;
import com.visioplanserver.repository.UserRepository;
import com.visioplanserver.service.CompanyService;
import com.visioplanserver.service.UserService;
import com.visioplanserver.service.exeption.UserNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final CompanyService companyService;


    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, CompanyService companyService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.companyService = companyService;
    }


    @Override
    public void registerUser(UserRegistrationDTO userRegistrationDTO) {
        UserEntity user = modelMapper.map(userRegistrationDTO, UserEntity.class);
        user.setCompany(companyService.getCompanyByName(userRegistrationDTO.companyName()));
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

    @Override
    public void updateUserProfile(UserProfileEditDTO userProfileEditDTO) {
            UserEntity userEntity = userRepository.findById(userProfileEditDTO.getId())
                    .orElseThrow(()-> new UserNotFoundException("User with id " + userProfileEditDTO.getId() + " not found!"));
            userEntity.setFirstName(userProfileEditDTO.getFirstName())
                    .setLastName(userProfileEditDTO.getLastName())
                    .setEmail(userProfileEditDTO.getEmail());
            userRepository.save(userEntity);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);

    }


}
