package com.visioplanserver.service.impl;

import com.visioplanserver.model.dto.AddBuildingDTO;
import com.visioplanserver.model.entity.BuildingEntity;
import com.visioplanserver.model.entity.FloorEntity;
import com.visioplanserver.model.view.BuildingNameDTO;
import com.visioplanserver.model.view.BuildingViewModel;
import com.visioplanserver.repository.BuildingRepository;
import com.visioplanserver.service.CloudImageService;
import com.visioplanserver.service.CompanyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BuildingServiceImplTest {
    @InjectMocks
    BuildingServiceImpl buildingService;
    @Mock
    BuildingRepository mockBuildingRepository;
    @Mock
    ModelMapper mockModelMapper;
    @Mock
    CloudImageService mockCloudImageService;
    @Mock
    CompanyService companyService;

    @BeforeEach
    void setUp() {
        buildingService = new BuildingServiceImpl(mockBuildingRepository, mockModelMapper, mockCloudImageService, companyService);
//        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllBuildings() {
        BuildingEntity buildingEntity = createTestBuilding();
        BuildingViewModel buildingViewModel = createTestBuildingViewModel();

        when(mockBuildingRepository.findAll())
                .thenReturn(List.of(buildingEntity));

        when(mockModelMapper.map(buildingEntity, BuildingViewModel.class))
                .thenReturn(buildingViewModel);

        List<BuildingViewModel> result = this.buildingService.getAllBuildings();

        assertEquals(1, result.size());
    }

    @Test
    void testAddNewBuilding() {
        AddBuildingDTO addBuildingDTO = createTestAddBuildingDTO();
        BuildingEntity buildingEntity = createTestBuilding();

        when(mockModelMapper.map(addBuildingDTO, BuildingEntity.class))
                .thenReturn(buildingEntity);

        when(mockCloudImageService.uploadImage(addBuildingDTO.imageFile()))
                .thenReturn("Test Image Url");

        buildingService.addNewBuilding(addBuildingDTO);
        verify(mockBuildingRepository).save(any());
    }

    @Test
    void testGetBuildingByName() {
        BuildingEntity buildingEntity = createTestBuilding();
        String buildingName = buildingEntity.getName();
        Long buildingId = buildingEntity.getId();

        when(mockBuildingRepository.findByName(buildingName))
                .thenReturn(Optional.of(buildingEntity));

        Long result = buildingService.getBuildingByName(buildingName);

        assertEquals(buildingId, result);
    }

    @Test
    void testGetAllBuildingsNamesAndFloors() {
        BuildingEntity buildingEntity = createTestBuilding();
        FloorEntity floorEntity = createTestFloor();

        buildingEntity.setFloors(Set.of(floorEntity));
        BuildingNameDTO buildingNameDTO = createTestBuildingNameDTO();

        when(mockBuildingRepository.findAll())
                .thenReturn(List.of(buildingEntity));

        when(mockModelMapper.map(buildingEntity, BuildingNameDTO.class))
                .thenReturn(buildingNameDTO);

        List<BuildingNameDTO> result = buildingService.getAllBuildingsNamesAndFloors();

        assertEquals(1, result.size());
        assertEquals(buildingNameDTO.getName(), result.get(0).getName());
    }

    @Test
    void testGetAllBuildingsNames() {
        BuildingEntity buildingEntity = createTestBuilding();
        FloorEntity floorEntity = createTestFloor();

        buildingEntity.setFloors(Set.of(floorEntity));
        BuildingNameDTO buildingNameDTO = createTestBuildingNameDTO();

        when(mockBuildingRepository.findAll())
                .thenReturn(List.of(buildingEntity));

        List<BuildingNameDTO> result = buildingService.getAllBuildingsNames();

        assertEquals(1, result.size());
        assertEquals(buildingNameDTO.getName(), result.get(0).getName());
    }

    private FloorEntity createTestFloor() {
        return new FloorEntity()
                .setNumber("Test Floor");
    }


    private BuildingNameDTO createTestBuildingNameDTO() {
        List<String> floors = List.of("Test Floor");
        return new BuildingNameDTO()
                .setName("Test Building")
                .setFloors(floors);
    }

    private AddBuildingDTO createTestAddBuildingDTO() {
        MultipartFile imageFile = new MockMultipartFile("test.jpg", "test.jpg", "image/jpeg", "test image".getBytes());
        return new AddBuildingDTO(
                "Test Building",
                "Test Address",
                "Test City",
                "Test Country",
                imageFile);
    }

    private BuildingEntity createTestBuilding() {
        return new BuildingEntity()
                .setName("Test Building")
                .setAddress("Test Address")
                .setCity("Test City")
                .setCountry("Test Country")
                .setImgUrl("Test Image Url");
    }

    private BuildingViewModel createTestBuildingViewModel() {
        return new BuildingViewModel()
                .setName("Test Building")
                .setAddress("Test Address")
                .setCity("Test City")
                .setCountry("Test Country")
                .setImgUrl("Test Image Url");
    }
}
