package com.visioplanserver.service.impl;

import com.visioplanserver.model.entity.FileEntity;
import com.visioplanserver.model.entity.FloorEntity;
import com.visioplanserver.model.entity.enums.BuldingDocumentationPartEnum;
import com.visioplanserver.model.entity.enums.DrawingTypeEnum;
import com.visioplanserver.model.entity.enums.FileExtensionEnum;
import com.visioplanserver.model.entity.enums.TextFileTypeEnum;
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

import java.time.LocalDateTime;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;
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
    void setUp(){
        serviceToTest = new FileServiceImpl(mockFileRepository, mockModelMapper, mockDropboxService, mockBuildingService, mockFloorService);
    }

    @Test
    void getAllFiles() {

    }

    private FileEntity createTestFile(){
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
