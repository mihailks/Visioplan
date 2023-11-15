package com.visioplanserver.service.impl;

import com.visioplanserver.model.dto.UserProfileEditDTO;
import com.visioplanserver.model.dto.UserRegistrationDTO;
import com.visioplanserver.model.entity.UserEntity;
import com.visioplanserver.model.entity.UserRoleEntity;
import com.visioplanserver.model.entity.enums.RolesEnum;
import com.visioplanserver.model.view.UserViewModel;
import com.visioplanserver.model.view.UserWithRoleViewModel;
import com.visioplanserver.repository.UserRepository;
import com.visioplanserver.repository.UserRoleRepository;
import com.visioplanserver.service.CompanyService;
import com.visioplanserver.service.UserService;
import com.visioplanserver.service.exeption.UserNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final CompanyService companyService;
    private final UserRoleRepository userRoleRepository;


    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, CompanyService companyService, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.companyService = companyService;
        this.userRoleRepository = userRoleRepository;
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
        UserEntity userEntity = userRepository.findByUsername(name)
                .orElseThrow(() -> new UserNotFoundException("User with name " + name + " not found!"));

        String companyName = userEntity.getCompany().getName();
        UserViewModel userViewModel = modelMapper.map(userEntity, UserViewModel.class);
        userViewModel.setCompany(companyName);
        return userViewModel;
    }

    @Override
    public void updateUserProfile(UserProfileEditDTO userProfileEditDTO) {
        UserEntity userEntity = userRepository.findById(userProfileEditDTO.getId())
                .orElseThrow(() -> new UserNotFoundException("User with id " + userProfileEditDTO.getId() + " not found!"));
        userEntity.setFirstName(userProfileEditDTO.getFirstName())
                .setLastName(userProfileEditDTO.getLastName())
                .setEmail(userProfileEditDTO.getEmail());
        userRepository.save(userEntity);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);

    }

    @Override
    public Page<UserWithRoleViewModel> findPage(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1, 10);
        return userRepository.findAll(pageable)
                .map(userEntity -> {
                    UserWithRoleViewModel userWithRoleViewModel = modelMapper.map(userEntity, UserWithRoleViewModel.class);
                    List<RolesEnum> roles = userEntity.getRole()
                            .stream()
                            .map(role -> RolesEnum.valueOf(role.getRole().name()))
                            .toList();
                    userWithRoleViewModel.setRole(roles);
                    return userWithRoleViewModel;
                });
    }

    @Override
    public void promoteUser(Long id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found!"));
        UserRoleEntity userRoleEntity = userRoleRepository.findByRole(RolesEnum.ADMIN)
                .orElseThrow(() -> new IllegalArgumentException("Role ADMIN not found!"));
        userEntity.getRole().add(userRoleEntity);
        userRepository.save(userEntity);
    }

    @Override
    public void demoteUser(Long id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found!"));
        UserRoleEntity userRoleEntity = userRoleRepository.findByRole(RolesEnum.ADMIN)
                .orElseThrow(() -> new IllegalArgumentException("Role ADMIN not found!"));
        userEntity.getRole().remove(userRoleEntity);
        userRepository.save(userEntity);
    }

    @Override
    public UserWithRoleViewModel findUserRoleByUsername(String name) {
        return userRepository.findByUsername(name)
                .map(userEntity -> {
                    UserWithRoleViewModel userWithRoleViewModel = modelMapper.map(userEntity, UserWithRoleViewModel.class);
                    List<RolesEnum> roles = userEntity.getRole()
                            .stream()
                            .map(role -> RolesEnum.valueOf(role.getRole().name()))
                            .toList();
                    userWithRoleViewModel.setRole(roles);
                    return userWithRoleViewModel;
                })
                .orElseThrow(() -> new UserNotFoundException("User with name " + name + " not found!"));
    }

    @Override
    public UserEntity findUserByUsernameEntity(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User with name " + username + " not found!"));
    }


}














