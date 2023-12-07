package com.visioplanserver.testUtils;

import com.visioplanserver.model.dto.CommentsAddDTO;
import com.visioplanserver.model.dto.CompanyRegistrationDTO;
import com.visioplanserver.model.dto.UserProfileEditDTO;
import com.visioplanserver.model.dto.UserRegistrationDTO;
import com.visioplanserver.model.entity.BuildingEntity;
import com.visioplanserver.model.entity.CompanyEntity;
import com.visioplanserver.model.entity.UserEntity;
import com.visioplanserver.model.entity.UserRoleEntity;
import com.visioplanserver.model.entity.enums.RolesEnum;
import com.visioplanserver.model.view.*;
import com.visioplanserver.repository.CompanyRepository;
import com.visioplanserver.repository.UserRepository;
import com.visioplanserver.repository.UserRoleRepository;
import com.visioplanserver.service.CompanyService;
import com.visioplanserver.service.impl.UserServiceImpl;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
@Component
public final class CreateTestData {

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


    public static UserEntity createTestUser() {
        List<UserRoleEntity> userRoleToAdd = new ArrayList<>();
        userRoleToAdd.add(new UserRoleEntity().setRole(RolesEnum.ADMIN));
        userRoleToAdd.add(new UserRoleEntity().setRole(RolesEnum.USER));
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setUsername("testUsername")
                .setEmail("testEmail")
                .setFirstName("testFirstName")
                .setLastName("testLastName")
                .setCompany(createTestCompany())
                .setRole(userRoleToAdd)
                .setPassword("testPassword");
        return userEntity;
    }

    public static CompanyEntity createTestCompany() {
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

    public static UserProfileEditDTO createTestUserProfileEditDTO() {
        return new UserProfileEditDTO()
                .setId(1L)
                .setFirstName("testFirstName")
                .setLastName("testLastName")
                .setEmail("testEmail");
    }

    public static UserWithRoleViewModel createTestUserWithRoleViewModel() {
        return new UserWithRoleViewModel()
                .setUsername("testUsername")
                .setEmail("testEmail")
                .setFirstName("testFirstName")
                .setLastName("testLastName")
                .setRole(List.of(RolesEnum.ADMIN, RolesEnum.USER));
    }

    public static UserRoleEntity createUserEntityRole() {
        return new UserRoleEntity()
                .setRole(RolesEnum.USER);
    }

    public static UserViewModel createTestUserViewModel() {
        UserViewModel userViewModel = new UserViewModel();
        userViewModel.setId(1L)
                .setUsername("testUsername")
                .setEmail("testEmail")
                .setFirstName("testFirstName")
                .setLastName("testLastName")
                .setCompany("testCompany");
        return userViewModel;
    }



    public static UserEntity createTestUserWithRole_User() {
        List<UserRoleEntity> userRoleToAdd = new ArrayList<>();
        userRoleToAdd.add(new UserRoleEntity().setRole(RolesEnum.USER));
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setUsername("testUsername")
                .setEmail("testEmail")
                .setFirstName("testFirstName")
                .setLastName("testLastName")
                .setCompany(createTestCompany())
                .setRole(userRoleToAdd)
                .setPassword("testPassword");
        return userEntity;
    }



    public static BuildingEntity createTestBuilding() {
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

    public static UserRegistrationDTO createUserRegistrationDTO() {
        return new UserRegistrationDTO(
                "username",
                "email",
                "password",
                "email",
                "firstName",
                "lastName",
                "companyName");

    }

    public static CommentsViewModel createTestCommentsViewModel() {
        return new CommentsViewModel()
                .setCommentsId(1L)
                .setTextContent("testComment")
                .setAuthor("testUser")
                .setFile(1L)
                .setUploaderEmail("testEmail");
    }

    public static CommentsAddDTO createTestCommentsAddDTO() {
        return new CommentsAddDTO()
                .setTextContent("testComment");
    }

    public static CompanyRegistrationDTO createTestCompanyRegistrationDTO() {
        return new CompanyRegistrationDTO(
                "testName",
                "testAddress",
                "testCity",
                "testCountry",
                "testPhone",
                "testEmail",
                "testWebsite");
    }

    public static CompanyViewModel createTestCompanyViewModel() {
        return new CompanyViewModel()
                .setId(1L)
                .setName("testName")
                .setAddress("testAddress")
                .setCity("testCity")
                .setCountry("testCountry")
                .setPhone("testPhone")
                .setEmail("testEmail")
                .setWebsite("testWebsite");
    }

    public static CompanyNameViewModel createTestCompanyNameViewModel() {
        return new CompanyNameViewModel()
                .setId(1L)
                .setName("testName");
    }

}
