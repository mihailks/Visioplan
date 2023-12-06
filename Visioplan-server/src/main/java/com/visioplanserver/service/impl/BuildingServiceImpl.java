package com.visioplanserver.service.impl;

import com.visioplanserver.model.dto.AddBuildingDTO;
import com.visioplanserver.model.entity.BuildingEntity;
import com.visioplanserver.model.entity.FloorEntity;
import com.visioplanserver.model.view.BuildingNameDTO;
import com.visioplanserver.model.view.BuildingViewModel;
import com.visioplanserver.model.view.CompanyNameViewModel;
import com.visioplanserver.model.view.StatsViewModel;
import com.visioplanserver.repository.BuildingRepository;
import com.visioplanserver.service.BuildingService;
import com.visioplanserver.service.CloudImageService;
import com.visioplanserver.service.CompanyService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BuildingServiceImpl implements BuildingService {
    private final BuildingRepository buildingRepository;
    private final ModelMapper modelMapper;
    private final CloudImageService cloudImageService;
    private final CompanyService companyService;

    public BuildingServiceImpl(BuildingRepository buildingRepository, ModelMapper modelMapper, CloudImageService cloudImageService, CompanyService companyService) {
        this.buildingRepository = buildingRepository;
        this.modelMapper = modelMapper;
        this.cloudImageService = cloudImageService;
        this.companyService = companyService;
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
        BuildingEntity buildingEntity = modelMapper.map(addBuildingDTO, BuildingEntity.class);

        if (imageFile != null) {
            String imageUrl = cloudImageService.uploadImage(imageFile);
            buildingEntity.setImgUrl(imageUrl);
        }

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

        List<BuildingEntity> all = buildingRepository.findAll();

        List<BuildingNameDTO> list =
                all
                        .stream()
                        .map(building -> {
                            BuildingNameDTO buildingNameDTO = new BuildingNameDTO();
                            buildingNameDTO.setName(building.getName());
                            Set<FloorEntity> floors = building.getFloors();

                                buildingNameDTO.setFloors(floors.stream().map(FloorEntity::getNumber).collect(Collectors.toList()));

                            return buildingNameDTO;
                        })
                        .toList();

        return list;
    }

    @Override
    public List<BuildingViewModel> getAllBuildingsByCompanyName(String username) {
        CompanyNameViewModel companyName = companyService.findCompanyByEmployeeName(username);
        List<BuildingViewModel> buildingsByCompanies =  buildingRepository.findAllCompaniesByCompanyName(companyName.getName());
        buildingsByCompanies.forEach(building -> building.setCompany(companyName));
        return buildingsByCompanies;
    }

    @Override
    public StatsViewModel getStats() {
        StatsViewModel totalEntriesCount = buildingRepository.getTotalEntriesCount();
        return totalEntriesCount;
    }
}





