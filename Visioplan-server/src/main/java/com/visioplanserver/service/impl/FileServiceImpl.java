package com.visioplanserver.service.impl;

import com.visioplanserver.model.dto.AddFileDTO;
import com.visioplanserver.model.entity.FileEntity;
import com.visioplanserver.model.entity.FloorEntity;
import com.visioplanserver.model.entity.UserEntity;
import com.visioplanserver.model.entity.enums.DrawingTypeEnum;
import com.visioplanserver.model.entity.enums.FileExtensionEnum;
import com.visioplanserver.model.entity.enums.TextFileTypeEnum;
import com.visioplanserver.model.view.FileViewModel;
import com.visioplanserver.repository.FileRepository;
import com.visioplanserver.service.*;
import com.visioplanserver.service.exeption.FileNotFoundException;
import jakarta.transaction.Transactional;
import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FileServiceImpl implements FileService {
    private final FileRepository fileRepository;
    private final ModelMapper modelMapper;
    private final DropboxService dropboxService;
    private final BuildingService buildingService;
    private final FloorService floorService;
    private final UserService userService;


    public FileServiceImpl(FileRepository fileRepository,ModelMapper modelMapper, DropboxService dropboxService, BuildingService buildingService, FloorService floorService, UserService userService) {
        this.fileRepository = fileRepository;
        this.modelMapper = modelMapper;
        this.dropboxService = dropboxService;
        this.buildingService = buildingService;
        this.floorService = floorService;
        this.userService = userService;
    }

    @Override
    public List<FileViewModel> getAllFiles() {
        return fileRepository.findAll()
                .stream()
                .map(file -> {
                    FileViewModel fileViewModel = modelMapper.map(file, FileViewModel.class);
                    fileViewModel.setFloor(file.getFloor().getNumber());
                    fileViewModel.setCommentsCounter(file.getComments().size());
                    return fileViewModel;
                }).collect(Collectors.toList());
    }

    @Override
    public Page<FileViewModel> findPage(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1, 10);
        return fileRepository.findAll(pageable)
                .map(file -> {
                    FileViewModel fileViewModel = modelMapper.map(file, FileViewModel.class);
                    fileViewModel.setFloor(file.getFloor().getNumber());
                    fileViewModel.setCommentsCounter(file.getComments().size());
                    return fileViewModel;
                });
    }
    //Pageable files by building name
    @Override
    public Page<FileViewModel> findPage(int pageNumber, String buildingName) {
        Pageable pageable = PageRequest.of(pageNumber - 1, 10);
        return fileRepository.findAllByFloor_Building_Name(buildingName, pageable)
                .map(file -> {
                    FileViewModel fileViewModel = modelMapper.map(file, FileViewModel.class);
                    fileViewModel.setFloor(file.getFloor().getNumber());
                    fileViewModel.setCommentsCounter(file.getComments().size());
                    return fileViewModel;
                });
    }

    @Override
    public void addNewFile(AddFileDTO addFileDTO, UserDetails uploader) {
        MultipartFile dataFile = addFileDTO.dataFile();
//        upload to dropbox
        String dataFileUrl = dropboxService.upload(dataFile);

        // file extension enum from file URL
        String fileExtension = dataFile.getOriginalFilename().substring(dataFile.getOriginalFilename().lastIndexOf(".") + 1);
        FileExtensionEnum fileExtensionEnum = null;
        for (FileExtensionEnum value : FileExtensionEnum.values()) {
            if (value.name().equals(fileExtension.toUpperCase())) {
                fileExtensionEnum = value;
                break;
            }
            fileExtensionEnum = FileExtensionEnum.NOT_SPECIFIED;
        }

//        String dataFileUrl = UUID.randomUUID().toString(); // for testing urls are unique
        //get building id
        String buildingName = addFileDTO.buildingName();
        Long buildingId = buildingService.getBuildingByName(buildingName);
        //get floor id
        String floorName = addFileDTO.floor();
        FloorEntity floorId = floorService.getFloorIdByNameAndBuildingId(floorName, buildingId);
        //get uploader
        UserEntity user = userService.findUserByUsernameEntity(uploader.getUsername());

        FileEntity file = modelMapper.map(addFileDTO, FileEntity.class);
        file.setFloor(floorId)
                .setUrl(dataFileUrl)
                .setFloor(floorId)
                .setUploadDate(LocalDateTime.now())
                .setUrl(dataFileUrl)
                .setTextFileType(file.getTextFileType() == null ? TextFileTypeEnum.NOT_SPECIFIED : file.getTextFileType())
                .setDrawingType(file.getDrawingType() == null ? DrawingTypeEnum.NOT_SPECIFIED : file.getDrawingType())
                .setComments(new HashSet<>())
                .setExtension(fileExtensionEnum)
                .setUploader(user);
        fileRepository.save(file);
    }

    @Transactional
    @Override
    public void deleteFile(Long id) {
        fileRepository.deleteById(id);
    }

    @Override
    public FileEntity findById(Long id) {
        return fileRepository.findById(id).orElseThrow(() -> new FileNotFoundException("File not found", id));
    }

}
