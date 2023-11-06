package com.visioplanserver.repository;

import com.visioplanserver.model.entity.BuildingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BuildingRepository extends JpaRepository<BuildingEntity, Long> {

    Optional<BuildingEntity> findByName(String buildingName);




}
