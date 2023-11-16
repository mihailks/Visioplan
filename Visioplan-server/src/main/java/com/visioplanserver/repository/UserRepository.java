package com.visioplanserver.repository;

import com.visioplanserver.model.entity.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
@Transactional
    Optional<UserEntity> findByUsername(String username);



}
