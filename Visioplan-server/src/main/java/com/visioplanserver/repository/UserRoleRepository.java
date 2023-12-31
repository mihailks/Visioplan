package com.visioplanserver.repository;

import com.visioplanserver.model.entity.UserRoleEntity;
import com.visioplanserver.model.entity.enums.RolesEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {

    Optional<UserRoleEntity> findByRole(RolesEnum role);

    List<UserRoleEntity> findAllByRoleIn(List<RolesEnum> roles);
}
