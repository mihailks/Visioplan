package com.visioplanserver.service;

import com.visioplanserver.model.dto.AddBuildingDTO;
import com.visioplanserver.model.view.BuildingViewModel;

import java.util.List;

public interface BuildingService {
    List<BuildingViewModel> getAllBuildings();

    void addNewBuilding(AddBuildingDTO addBuildingDTO);
}
