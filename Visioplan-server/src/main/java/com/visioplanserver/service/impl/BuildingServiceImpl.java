package com.visioplanserver.service.impl;

import com.visioplanserver.model.entity.BuildingEntity;
import com.visioplanserver.model.view.ProjectViewModel;
import com.visioplanserver.repository.BuildingRepository;
import com.visioplanserver.service.BuildingService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuildingServiceImpl implements BuildingService {
    private final BuildingRepository buildingRepository;
    private final ModelMapper modelMapper;

    public BuildingServiceImpl(BuildingRepository buildingRepository, ModelMapper modelMapper) {
        this.buildingRepository = buildingRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<ProjectViewModel> getAllBuildings() {
        List<BuildingEntity> buildings = buildingRepository.findAll();
        return buildings.stream()
                .map(building -> modelMapper.map(building, ProjectViewModel.class))
                .toList();
    }
}
