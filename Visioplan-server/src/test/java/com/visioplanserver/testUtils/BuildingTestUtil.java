package com.visioplanserver.testUtils;

import com.visioplanserver.model.entity.BuildingEntity;
import com.visioplanserver.model.entity.CompanyEntity;
import com.visioplanserver.model.entity.UserEntity;
import com.visioplanserver.model.entity.UserRoleEntity;
import com.visioplanserver.model.entity.enums.RolesEnum;
import com.visioplanserver.repository.BuildingRepository;
import org.hsqldb.rights.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class BuildingTestUtil {
    @Autowired
    private BuildingRepository buildingRepository;

    public void cleanUp() {
        buildingRepository.deleteAll();
    }

    public BuildingEntity createTestBuilding(String name) {
        BuildingEntity buildingEntity = createBuilding(name);
        CompanyEntity companyEntity = createCompany();
        UserEntity userEntity = createUser();
        companyEntity.getEmployees().add(userEntity);
        buildingEntity.getCompanies().add(companyEntity);

        return buildingRepository.save(buildingEntity);
    }

    private UserEntity createUser() {
        List<UserRoleEntity> roles = new ArrayList<>();
        roles.add(new UserRoleEntity().setRole(RolesEnum.USER));
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("username")
                .setEmail("test@abv.bg")
                .setFirstName("testFirstName")
                .setLastName("testLastName")
                .setRole(roles)
                .setPassword("testPassword");
        return userEntity;
    }

    private CompanyEntity createCompany() {
        CompanyEntity companyEntity = new CompanyEntity();
        companyEntity.setName("Softuni")
                .setAddress("Sofia")
                .setEmail("test@gmail.com")
                .setCountry("Bulgaria")
                .setCity("Sofia")
                .setEmployees(new HashSet<>());
        return companyEntity;
    }

    private BuildingEntity createBuilding(String name) {
        BuildingEntity buildingEntity = new BuildingEntity();
        buildingEntity.setName(name)
                .setAddress("Sofia")
                .setCity("Sofia")
                .setCountry("Bulgaria")
                .setCompanies(new HashSet<>());
        return buildingEntity;
    }


}
