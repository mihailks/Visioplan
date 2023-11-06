package com.visioplanserver.service.impl;

import com.visioplanserver.model.dto.AddBuildingDTO;
import com.visioplanserver.model.entity.BuildingEntity;
import com.visioplanserver.model.view.BuildingNameDTO;
import com.visioplanserver.model.view.BuildingViewModel;
import com.visioplanserver.repository.BuildingRepository;
import com.visioplanserver.service.BuildingService;
import com.visioplanserver.service.CloudImageService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class BuildingServiceImpl implements BuildingService {
    private final BuildingRepository buildingRepository;
    private final ModelMapper modelMapper;
    private final CloudImageService cloudImageService;

    public BuildingServiceImpl(BuildingRepository buildingRepository, ModelMapper modelMapper, CloudImageService cloudImageService) {
        this.buildingRepository = buildingRepository;
        this.modelMapper = modelMapper;
        this.cloudImageService = cloudImageService;
    }


    @Override
    public List<BuildingViewModel> getAllBuildings() {
        List<BuildingEntity> buildings = buildingRepository.findAll();
        return buildings.stream()
                .map(building -> modelMapper.map(building, BuildingViewModel.class))
                .toList();
    }

    @Override
    public void addNewBuilding(AddBuildingDTO addBuildingDTO) {
        MultipartFile imageFile = addBuildingDTO.imageFile();
        String imageUrl = cloudImageService.uploadImage(imageFile);
        BuildingEntity buildingEntity = modelMapper.map(addBuildingDTO, BuildingEntity.class);

        buildingEntity.setImgUrl(imageUrl);

        buildingRepository.save(buildingEntity);
    }

    @Override
    public Long getBuildingByName(String buildingName) {
        return buildingRepository.findByName(buildingName)
                .map(BuildingEntity::getId)
                .orElse(null);
    }

    @Override
    public List<BuildingNameDTO> getAllBuildingsNamesAndFloors() {
        return buildingRepository.findAll()
                .stream()
                .map(building -> modelMapper.map(building, BuildingNameDTO.class))
                .toList();
    }

    @Override
    public List<BuildingNameDTO> getAllBuildingsNames() {
        return buildingRepository.findAll()
                .stream()
                .map(building -> modelMapper.map(building, BuildingNameDTO.class))
                .toList();
    }
}





