package com.visioplanserver.service.impl;

import com.visioplanserver.model.entity.FloorEntity;
import com.visioplanserver.model.view.FloorNameDTO;
import com.visioplanserver.repository.FloorRepository;
import com.visioplanserver.service.FloorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FloorServiceImpl implements FloorService {
    private final FloorRepository floorRepository;

    public FloorServiceImpl(FloorRepository floorRepository) {
        this.floorRepository = floorRepository;
    }

    @Override
    public FloorEntity getFloorIdByNameAndBuildingId(String floorName, Long buildingId) {
        return floorRepository.findIdByNumberAndBuildingId(floorName, buildingId)
                .orElseThrow(() -> new IllegalArgumentException("Floor with name " + floorName + " and building id " + buildingId + " does not exist!"));

    }

    @Override
    public List<FloorNameDTO> getAllFloorsNamesByBuildingName(String buildingName) {
        return floorRepository.findAllByBuildingId(buildingName);
    }
}
