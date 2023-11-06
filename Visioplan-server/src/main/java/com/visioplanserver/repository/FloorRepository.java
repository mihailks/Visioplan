package com.visioplanserver.repository;

import com.visioplanserver.model.entity.FloorEntity;
import com.visioplanserver.model.view.FloorNameDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FloorRepository extends JpaRepository<FloorEntity, Long> {

    Long findIdByNumber(String floorName);

    Optional<FloorEntity> findIdByNumberAndBuildingId(String floorName, Long buildingId);

    @Query("SELECT new com.visioplanserver.model.view.FloorNameDTO(f.number) " +
            "FROM FloorEntity f " +
            "WHERE f.building.name = :buildingName")
    List<FloorNameDTO> findAllByBuildingId(String buildingName);
}
