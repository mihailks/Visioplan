package com.visioplanserver.service.impl;

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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

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
