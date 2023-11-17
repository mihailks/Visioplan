package com.visioplanserver.service.impl;

import com.visioplanserver.model.dto.UserProfileEditDTO;
import com.visioplanserver.model.dto.UserRegistrationDTO;
import com.visioplanserver.model.entity.*;
import com.visioplanserver.model.entity.enums.RolesEnum;
import com.visioplanserver.model.view.UserViewModel;
import com.visioplanserver.repository.CompanyRepository;
import com.visioplanserver.repository.UserRepository;
import com.visioplanserver.repository.UserRoleRepository;
import com.visioplanserver.service.CompanyService;
import com.visioplanserver.service.exeption.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
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
    void userNotFoundShouldThrow() {
        assertThrows(UserNotFoundException.class, () -> {
            serviceToTest.findUserByUsername("testUsername");
        });
    }

    @Test
    void testRegisterUser() {
        UserRegistrationDTO userRegistrationDTO = createUserRegistrationDTO();

            when(mockModelMapper.map(userRegistrationDTO, UserEntity.class))
                    .thenReturn(createTestUser());

    }

    private UserRegistrationDTO createUserRegistrationDTO() {
        return new UserRegistrationDTO(
                "username",
                "password",
                "password",
                "email",
                "firstName",
                "lastName",
                "companyName");

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
        UserEntity testUserEntity = createTestUser();

        when(mockUserRepository.save(Mockito.any(UserEntity.class)))
                .thenReturn(testUserEntity);

        mockUserRepository.save(testUserEntity);

//        assertEquals(1, mockUserRepository.count());

        mockUserRepository.deleteById(testUserEntity.getId());

        assertEquals(0, mockUserRepository.count());

//        Optional<UserEntity> byUsername = mockUserRepository.findByUsername(testUserEntity.getUsername());
//        assertEquals(Optional.empty(), byUsername);

//        System.out.println(mockUserRepository.findAll().size());

    }

    private UserProfileEditDTO createTestUserProfileEditDTO() {
        return new UserProfileEditDTO()
                .setId(1L)
                .setFirstName("testFirstName")
                .setLastName("testLastName")
                .setEmail("testEmail");
    }

    private UserViewModel createTestUserViewModel() {
        UserViewModel userViewModel = new UserViewModel();
        userViewModel.setId(1L)
                .setUsername("testUsername")
                .setEmail("testEmail")
                .setFirstName("testFirstName")
                .setLastName("testLastName")
                .setCompany("testCompany");
        return userViewModel;
    }

    private UserEntity createTestUser() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setUsername("testUsername")
                .setEmail("testEmail")
                .setFirstName("testFirstName")
                .setLastName("testLastName")
                .setCompany(createTestCompany())
                .setRole(List.of(
                        new UserRoleEntity().setRole(RolesEnum.ADMIN),
                        new UserRoleEntity().setRole(RolesEnum.USER)
                ))
                .setPassword("testPassword");
        return userEntity;
    }

    private CompanyEntity createTestCompany() {
        CompanyEntity companyEntity = new CompanyEntity();
        companyEntity.setId(2L);
        companyEntity.setName("testName")
                .setAddress("testAddress")
                .setCity("testCity")
                .setCountry("testCountry")
                .setPhone("testPhone")
                .setEmail("testEmail")
                .setWebsite("testWebsite")
                .setBuildings(new HashSet<>());
        return companyEntity;
    }

    private BuildingEntity createTestBuilding() {
        BuildingEntity buildingEntity = new BuildingEntity();
        buildingEntity.setId(3L);
        buildingEntity.setName("testName")
                .setAddress("testAddress")
                .setCity("testCity")
                .setCountry("testCountry")
                .setImgUrl("testImgUrl")
                .setCompanies(new HashSet<>())
                .setFloors(new HashSet<>());
        return buildingEntity;
    }


}
