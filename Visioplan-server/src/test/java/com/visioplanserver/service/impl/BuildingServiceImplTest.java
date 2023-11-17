package com.visioplanserver.service.impl;

import com.visioplanserver.model.dto.AddBuildingDTO;
import com.visioplanserver.model.entity.BuildingEntity;
import com.visioplanserver.model.view.BuildingViewModel;
import com.visioplanserver.repository.BuildingRepository;
import com.visioplanserver.service.CloudImageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
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

    @BeforeEach
    void setUp() {
        buildingService = new BuildingServiceImpl(mockBuildingRepository, mockModelMapper, mockCloudImageService);
//        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddNewBuilding(){
        AddBuildingDTO addBuildingDTO = createTestAddBuildingDTO();

        BuildingEntity buildingEntity = createTestBuilding();

        buildingEntity.setImgUrl(addBuildingDTO.imageFile().getName());

        when(mockBuildingRepository.save(Mockito.any(BuildingEntity.class)))
                .thenReturn(buildingEntity);


//        buildingService.addNewBuilding(addBuildingDTO);

        Mockito.verify(mockBuildingRepository)
                .save(Mockito.any(BuildingEntity.class));

    }


    @Test
    void testGetAllBuildings(){
        BuildingEntity buildingEntity = createTestBuilding();
        BuildingViewModel buildingViewModel = createTestBuildingViewModel();

        when(mockBuildingRepository.findAll())
                .thenReturn(List.of(buildingEntity));

        when(mockModelMapper.map(buildingEntity, BuildingViewModel.class))
                .thenReturn(buildingViewModel);

        List<BuildingViewModel> result = this.buildingService.getAllBuildings();

        assertEquals(1, result.size());
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
