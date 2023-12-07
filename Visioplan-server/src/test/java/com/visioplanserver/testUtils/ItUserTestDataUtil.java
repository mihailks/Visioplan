package com.visioplanserver.testUtils;

import com.visioplanserver.model.entity.CompanyEntity;
import com.visioplanserver.model.entity.UserEntity;
import com.visioplanserver.model.entity.UserRoleEntity;
import com.visioplanserver.model.entity.enums.RolesEnum;
import com.visioplanserver.repository.CompanyRepository;
import com.visioplanserver.repository.UserRepository;
import com.visioplanserver.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class ItUserTestDataUtil {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private CompanyRepository companyRepository;

    public UserEntity createTestUser(String username) {
        return createUser(username, List.of(RolesEnum.USER));
    }

    public UserEntity createTestAdmin(String username) {
        return createUser(username, List.of(RolesEnum.ADMIN));
    }

    private UserEntity createUser(String username, List<RolesEnum> roles) {
        List<UserRoleEntity> allByRoleIn = userRoleRepository.findAllByRoleIn(roles);
        allByRoleIn.add(new UserRoleEntity().setRole(roles.get(0)));

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(username)
                .setEmail(username + "@abv.bg")
                .setFirstName("testFirstName")
                .setLastName("testLastName")
                .setRole(allByRoleIn)
                .setPassword("testPassword");
        return userRepository.save(userEntity);
    }

    public void cleanUp() {
        userRepository.deleteAll();
        companyRepository.deleteAll();
    }
}

//        CompanyEntity companyEntity = new CompanyEntity();
//        companyEntity.setName("Softuni")
//                .setAddress("Sofia")
//                .setEmail("university@softuni.bg")
//                .setPhone("+ 359 899 55 55 92")
//                .setWebsite("https://softuni.bg/")
//                .setAddress("София, ж.к. Младост 4, бул. Александър Малинов №78 ПК: 1799")
//                .setCity("Sofia")
//                .setCountry("Bulgaria");
//        companyRepository.save(companyEntity);
