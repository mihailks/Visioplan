package com.visioplanserver.service;

import com.visioplanserver.model.entity.FloorEntity;
import com.visioplanserver.model.view.FloorNameDTO;

import java.util.List;

public interface FloorService {
    FloorEntity getFloorIdByNameAndBuildingId(String floorName, Long buildingId);

    List<FloorNameDTO> getAllFloorsNamesByBuildingName(String buildingName);
}
