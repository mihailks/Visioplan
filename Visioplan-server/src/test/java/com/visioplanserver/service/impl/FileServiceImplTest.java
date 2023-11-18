package com.visioplanserver.service.impl;

import com.visioplanserver.model.dto.AddFileDTO;
import com.visioplanserver.model.entity.BuildingEntity;
import com.visioplanserver.model.entity.FileEntity;
import com.visioplanserver.model.entity.FloorEntity;
import com.visioplanserver.model.entity.enums.BuldingDocumentationPartEnum;
import com.visioplanserver.model.entity.enums.DrawingTypeEnum;
import com.visioplanserver.model.entity.enums.FileExtensionEnum;
import com.visioplanserver.model.entity.enums.TextFileTypeEnum;
import com.visioplanserver.model.view.FileViewModel;
import com.visioplanserver.repository.FileRepository;
import com.visioplanserver.service.BuildingService;
import com.visioplanserver.service.DropboxService;
import com.visioplanserver.service.FloorService;
import com.visioplanserver.service.exeption.FileNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class FileServiceImplTest {

    FileServiceImpl serviceToTest;
    @Mock
    private FileRepository mockFileRepository;
    @Mock
    private DropboxService mockDropboxService;
    @Mock
    private BuildingService mockBuildingService;
    @Mock
    private FloorService mockFloorService;
    @Mock
    ModelMapper mockModelMapper;

    @BeforeEach
    void setUp() {
        serviceToTest = new FileServiceImpl(mockFileRepository, mockModelMapper, mockDropboxService, mockBuildingService, mockFloorService);
    }

    @Test
    void getAllFiles() {
        List<FileEntity> files = new ArrayList<>();
        files.add(createTestFile());
        when(mockFileRepository.findAll())
                .thenReturn(files);
        FileViewModel fileViewModel = createTestFileViewModel();
        when(mockModelMapper.map(any(FileEntity.class), any()))
                .thenReturn(fileViewModel);

        List<FileViewModel> result = serviceToTest.getAllFiles();

        assertEquals(1, result.size());
        assertEquals(fileViewModel.getName(), result.get(0).getName());
        assertEquals(fileViewModel.getUrl(), result.get(0).getUrl());
        assertEquals(fileViewModel.getUploadDate(), result.get(0).getUploadDate());
        assertEquals(fileViewModel.getExtension(), result.get(0).getExtension());
        assertEquals(fileViewModel.getPart(), result.get(0).getPart());
        assertEquals(fileViewModel.getFloor(), result.get(0).getFloor());
        assertEquals(fileViewModel.getCommentsCounter(), result.get(0).getCommentsCounter());

    }

    @Test
    void testFindPage() {

        List<FileEntity> files = new ArrayList<>();
        files.add(createTestFile());
        Page<FileEntity> page = new PageImpl<>(files);
        FileViewModel fileViewModel = createTestFileViewModel();
        when(mockModelMapper.map(any(FileEntity.class), any()))
                .thenReturn(fileViewModel);
        when(mockFileRepository.findAll(any(PageRequest.class)))
                .thenReturn(page);

        Page<FileViewModel> result = serviceToTest.findPage(1);

        assertEquals(1, result.getTotalElements());
        assertEquals(1, result.getTotalPages());
        assertEquals(1, result.getContent().size());
    }

    @Test
    void testAddNewFile() {
        MultipartFile dataFile = createTestMultipartFile();
        AddFileDTO addFileDTO = createTestAddFileDTO();
        FileEntity fileEntity = createTestFile();

        when(mockModelMapper.map(addFileDTO, FileEntity.class))
                .thenReturn(fileEntity);
        when(mockFileRepository.save(fileEntity))
                .thenReturn(fileEntity);
        when(mockBuildingService.getBuildingByName(any()))
                .thenReturn(1L);
        when(mockFloorService.getFloorIdByNameAndBuildingId(any(), any()))
                .thenReturn(createTestFloor());

        serviceToTest.addNewFile(addFileDTO);
        verify(mockFileRepository).save(any());
    }

    @Test
    void testDeleteFile() {
        serviceToTest.deleteFile(1L);
        verify(mockFileRepository).deleteById(1L);

    }

    @Test
    void testFindByIdShouldThrow(){
        assertThrows(FileNotFoundException.class, () -> {
            serviceToTest.findById(1L);
        });
    }

    @Test
    void testFindById(){
        //TODO: fix the service to return FileViewModel not and Entity
        FileEntity fileEntity = createTestFile();
        when(mockFileRepository.findById(1L))
                .thenReturn(Optional.of(fileEntity));
        FileViewModel fileViewModel = createTestFileViewModel();
        FileEntity result = serviceToTest.findById(1L);

        assertEquals(fileViewModel.getName(), result.getName());
        assertEquals(fileViewModel.getUrl(), result.getUrl());
        //TODO: fix this method is service!!!
//        assertEquals(fileViewModel.getUploadDate(), result.getUploadDate());
        assertEquals(fileViewModel.getExtension(), result.getExtension());
        assertEquals(fileViewModel.getPart(), result.getPart());

    }

    private MultipartFile createTestMultipartFile() {
        return new MockMultipartFile("test", "test", "test", "test".getBytes());
    }

    private FloorEntity createTestFloor() {
        return new FloorEntity()
                .setNumber("Test Floor");
    }


    private AddFileDTO createTestAddFileDTO() {
        return new AddFileDTO(
                "test",
                "test",
                BuldingDocumentationPartEnum.PLUMBING,
                DrawingTypeEnum.SITE_PLAN,
                TextFileTypeEnum.NOT_SPECIFIED,
                "test",
                FileExtensionEnum.DWG,
                new MockMultipartFile("test", "test", "test", "test".getBytes())
        );

    }

    private FileViewModel createTestFileViewModel() {
        return new FileViewModel()
                .setId(1L)
                .setName("test")
                .setUrl("test")
                .setUploadDate(LocalDateTime.now())
                .setPart(BuldingDocumentationPartEnum.PLUMBING)
                .setFloor("1")
                .setCommentsCounter(0)
                .setExtension(FileExtensionEnum.PDF);
    }


    private FileEntity createTestFile() {
        return new FileEntity()
                .setName("test")
                .setUrl("test")
                .setUploadDate(LocalDateTime.now())
                .setDrawingType(DrawingTypeEnum.SITE_PLAN)
                .setTextFileType(TextFileTypeEnum.NOT_SPECIFIED)
                .setExtension(FileExtensionEnum.PDF)
                .setPart(BuldingDocumentationPartEnum.PLUMBING)
                .setFloor(new FloorEntity())
                .setComments(new HashSet<>());
    }
}
