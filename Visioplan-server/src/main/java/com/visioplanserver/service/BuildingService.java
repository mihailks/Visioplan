package com.visioplanserver.service;

import com.visioplanserver.model.dto.AddBuildingDTO;
import com.visioplanserver.model.view.BuildingNameDTO;
import com.visioplanserver.model.view.BuildingViewModel;

import java.util.List;

public interface BuildingService {
    List<BuildingViewModel> getAllBuildings();

    void addNewBuilding(AddBuildingDTO addBuildingDTO);

    Long getBuildingByName(String buildingName);

    List<BuildingNameDTO> getAllBuildingsNamesAndFloors();

    List<BuildingNameDTO> getAllBuildingsNames();

    List<BuildingViewModel> getAllBuildingsByCompanyName(String username);
}
