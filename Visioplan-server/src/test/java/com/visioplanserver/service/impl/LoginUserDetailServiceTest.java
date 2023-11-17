package com.visioplanserver.service.impl;

import com.visioplanserver.model.entity.UserEntity;
import com.visioplanserver.model.entity.UserRoleEntity;
import com.visioplanserver.model.entity.enums.RolesEnum;
import com.visioplanserver.repository.UserRepository;
import org.apache.catalina.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoginUserDetailServiceTest {

    private LoginUserDetailService serviceToTest;
    @Mock
    private UserRepository mockUserRepository;

    @BeforeEach
    void setUp() {
        serviceToTest = new LoginUserDetailService(mockUserRepository);
    }

    @Test
    void testMock() {
        // create new entity
        UserEntity userEntity = new UserEntity().setUsername("test");
        // teach the mockrepository to return the entity when findByUsername is called
        when(mockUserRepository.findByUsername("test"))
                .thenReturn(Optional.of(userEntity));
        // call the method to test
        Optional<UserEntity> userOpt = mockUserRepository.findByUsername("test");

        UserEntity user = userOpt.get();
        assertEquals(user.getUsername(), userEntity.getUsername());
    }

    @Test
    void testUserNotFoundShouldThrow() {
        assertThrows(UsernameNotFoundException.class, () -> {
            serviceToTest.loadUserByUsername("testUsername");
        });
    }

    @Test
    void testUserFoundException() {
        UserEntity testUserEntity = createTestUser();

        when(mockUserRepository.findByUsername(testUserEntity.getUsername()))
                .thenReturn(Optional.of(testUserEntity));


        UserDetails userDetails = serviceToTest.loadUserByUsername(testUserEntity.getUsername());

        assertNotNull(userDetails);
        assertEquals(testUserEntity.getUsername(), userDetails.getUsername(), "Username should match");
        assertEquals(testUserEntity.getPassword(), userDetails.getPassword(), "Password should match");
        assertEquals(2, userDetails.getAuthorities().size(), "Number of roles should match");
        assertTrue(containsAuthority(userDetails, "ROLE_" + RolesEnum.ADMIN.name()), "The user user is not admin");
        assertTrue(containsAuthority(userDetails, "ROLE_" + RolesEnum.USER.name()), "The user user is not user");
    }

    private boolean containsAuthority(UserDetails userDetails, String expectedAuthority){
        return userDetails
                .getAuthorities()
                .stream()
                .anyMatch(a->a.getAuthority().equals(expectedAuthority));
    }


    private static UserEntity createTestUser() {
        return new UserEntity()
                .setUsername("username")
                .setFirstName("firstName")
                .setLastName("lastName")
                .setPassword("password")
                .setEmail("email@test.com")
                .setRole(List.of(
                        new UserRoleEntity().setRole(RolesEnum.ADMIN),
                        new UserRoleEntity().setRole(RolesEnum.USER)
                ));
    }
}
