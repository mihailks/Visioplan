package com.visioplanserver.service.impl;

import com.visioplanserver.model.dto.AddFileDTO;
import com.visioplanserver.model.entity.FileEntity;
import com.visioplanserver.model.entity.FloorEntity;
import com.visioplanserver.model.view.FileViewModel;
import com.visioplanserver.repository.FileRepository;
import com.visioplanserver.service.BuildingService;
import com.visioplanserver.service.DropboxService;
import com.visioplanserver.service.FileService;
import com.visioplanserver.service.FloorService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileServiceImpl implements FileService {
    private final FileRepository fileRepository;
    private final ModelMapper modelMapper;
    private final DropboxService dropboxService;
    private final BuildingService buildingService;
    private final FloorService floorService;


    public FileServiceImpl(FileRepository fileRepository, ModelMapper modelMapper, DropboxService dropboxService, BuildingService buildingService, FloorService floorService) {
        this.fileRepository = fileRepository;
        this.modelMapper = modelMapper;
        this.dropboxService = dropboxService;
        this.buildingService = buildingService;
        this.floorService = floorService;
    }

    @Override
    public List<FileViewModel> getAllFiles() {
        return fileRepository.findAll()
                .stream()
                .map(file -> {
                    FileViewModel fileViewModel = modelMapper.map(file, FileViewModel.class);
                    fileViewModel.setCommentsCounter(file.getComments().size());
                    return fileViewModel;
                }).collect(Collectors.toList());
    }

    @Override
    public void addNewFile(AddFileDTO addFileDTO) {
        MultipartFile dataFile = addFileDTO.dataFile();
        //upload to dropbox
//        String dataFileUrl = dropboxService.testMyUpload(dataFile);
        //get building id
        String buildingName = addFileDTO.buildingName();
        Long buildingId = buildingService.getBuildingByName(buildingName);
        //get floor id
        String floorName = addFileDTO.floor();
        FloorEntity floorId = floorService.getFloorIdByNameAndBuildingId(floorName,buildingId);

        FileEntity file = modelMapper.map(addFileDTO, FileEntity.class);
        file.setFloor(floorId);
        fileRepository.save(file);
    }

}
