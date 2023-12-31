package com.visioplanserver.service.impl;

import com.visioplanserver.model.dto.UserProfileEditDTO;
import com.visioplanserver.model.dto.UserRegistrationDTO;
import com.visioplanserver.model.entity.*;
import com.visioplanserver.model.entity.enums.RolesEnum;
import com.visioplanserver.model.view.UserViewModel;
import com.visioplanserver.model.view.UserWithRoleViewModel;
import com.visioplanserver.repository.CompanyRepository;
import com.visioplanserver.repository.UserRepository;
import com.visioplanserver.repository.UserRoleRepository;
import com.visioplanserver.service.CompanyService;
import com.visioplanserver.service.exeption.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static com.visioplanserver.testUtils.CreateTestData.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    UserRepository mockUserRepository;
    private UserServiceImpl serviceToTest;
    @Mock
    private CompanyRepository mockCompanyRepository;
    @Mock
    private CompanyService mockCompanyService;
    @Mock
    private ModelMapper mockModelMapper;
    @Mock
    private PasswordEncoder mockPasswordEncoder;

    @Mock
    private UserRoleRepository mockUserRoleRepository;
    @Mock
    private ApplicationEventPublisher mockApplicationEventPublisher;


    @BeforeEach
    void setUp() {
        serviceToTest = new UserServiceImpl(
                mockUserRepository,
                mockModelMapper,
                mockPasswordEncoder,
                mockCompanyService,
                mockUserRoleRepository,
                mockApplicationEventPublisher
        );
    }

    @Test
    void testRegisterUser() {
        UserRegistrationDTO userRegistrationDTO = createUserRegistrationDTO();
        UserEntity userEntity = createTestUser();

        when(mockModelMapper.map(userRegistrationDTO, UserEntity.class))
                .thenReturn(userEntity);

        when(mockCompanyService.getCompanyByName(userRegistrationDTO.companyName()))
                .thenReturn(createTestCompany());

        when(mockUserRoleRepository.findByRole(RolesEnum.USER))
                .thenReturn(Optional.of(new UserRoleEntity().setRole(RolesEnum.USER)));

        when(mockPasswordEncoder.encode(userRegistrationDTO.password()))
                .thenReturn("testPassword");
        serviceToTest.registerUser(userRegistrationDTO);
        verify(mockUserRepository).save(any());

    }

    @Test
    void userNotFoundShouldThrow() {
        assertThrows(UserNotFoundException.class, () -> {
            serviceToTest.findUserByUsername("testUsername");
        });
    }

    @Test
    void testFindUserByUsername() {
        UserEntity testUserEntity = createTestUser();

        when(mockUserRepository.findByUsername(testUserEntity.getUsername()))
                .thenReturn(Optional.of(testUserEntity));

        when(mockModelMapper.map(testUserEntity, UserViewModel.class))
                .thenReturn(createTestUserViewModel());

        UserViewModel userViewModel = serviceToTest.findUserByUsername(testUserEntity.getUsername());
        assertEquals(testUserEntity.getUsername(), userViewModel.getUsername(), "Username should match");
        assertEquals(testUserEntity.getEmail(), userViewModel.getEmail(), "Email should match");
        assertEquals(testUserEntity.getFirstName(), userViewModel.getFirstName(), "First name should match");
        assertEquals(testUserEntity.getLastName(), userViewModel.getLastName(), "Last name should match");
        assertEquals(testUserEntity.getCompany().getName(), userViewModel.getCompany(), "Company name should match");
    }

    @Test
    void testUpdateUserProfile() {
        UserEntity testUserEntity = createTestUser();
        UserProfileEditDTO userProfileEditDTO = createTestUserProfileEditDTO();

        when(mockUserRepository.findById(testUserEntity.getId()))
                .thenReturn(Optional.of(testUserEntity));

        serviceToTest.updateUserProfile(userProfileEditDTO);

        assertEquals(userProfileEditDTO.getFirstName(), testUserEntity.getFirstName(), "First name should match");
        assertEquals(userProfileEditDTO.getLastName(), testUserEntity.getLastName(), "Last name should match");
        assertEquals(userProfileEditDTO.getEmail(), testUserEntity.getEmail(), "Email should match");
    }

    @Test
    void testDeleteUser() {
        Long id = 1L;
        serviceToTest.deleteUser(id);
        verify(mockUserRepository).deleteById(id);
    }



    @Test
    void testPromoteUser() {
        UserEntity testUserEntity = createTestUserWithRole_User();
        UserRoleEntity userRoleEntity = createUserEntityRole();

        when(mockUserRoleRepository.findByRole(RolesEnum.ADMIN))
                .thenReturn(Optional.of(userRoleEntity));

        when(mockUserRepository.findById(testUserEntity.getId()))
                .thenReturn(Optional.of(testUserEntity));

        serviceToTest.promoteUser(testUserEntity.getId());

        assertEquals(2, testUserEntity.getRole().size(), "User should have 2 roles");
    }

    @Test
    void testDemoteUser() {
        UserEntity testUserEntity = createTestUser();
        UserRoleEntity userRoleEntity = createUserEntityRole();

        when(mockUserRoleRepository.findByRole(RolesEnum.ADMIN))
                .thenReturn(Optional.of(userRoleEntity));

        when(mockUserRepository.findById(testUserEntity.getId()))
                .thenReturn(Optional.of(testUserEntity));

        serviceToTest.demoteUser(testUserEntity.getId());

        assertEquals(1, testUserEntity.getRole().size(), "User should have 2 roles");
    }

    @Test
    void testFindUserRoleByUsername() {
        UserEntity testUserEntity = createTestUser();
        UserRoleEntity userRoleEntity = createUserEntityRole();

        when(mockUserRepository.findByUsername(testUserEntity.getUsername()))
                .thenReturn(Optional.of(testUserEntity));

        when(mockModelMapper.map(testUserEntity, UserWithRoleViewModel.class))
                .thenReturn(createTestUserWithRoleViewModel());

        UserWithRoleViewModel userWithRoleViewModel = serviceToTest.findUserRoleByUsername(testUserEntity.getUsername());

        assertEquals(testUserEntity.getUsername(), userWithRoleViewModel.getUsername(), "Username should match");
        assertEquals(testUserEntity.getEmail(), userWithRoleViewModel.getEmail(), "Email should match");
        assertEquals(testUserEntity.getFirstName(), userWithRoleViewModel.getFirstName(), "First name should match");
        assertEquals(testUserEntity.getLastName(), userWithRoleViewModel.getLastName(), "Last name should match");
        assertEquals(2, userWithRoleViewModel.getRole().size(), "User should have 2 roles");
    }




}
