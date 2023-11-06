package com.visioplanserver.service.impl;

import com.visioplanserver.model.dto.AddFileDTO;
import com.visioplanserver.model.dto.TESTAddFileDTO;
import com.visioplanserver.model.view.FileViewModel;
import com.visioplanserver.repository.FileRepository;
import com.visioplanserver.service.DropboxService;
import com.visioplanserver.service.FileService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileServiceImpl implements FileService {
    private final FileRepository fileRepository;
    private final ModelMapper modelMapper;
    private final DropboxService dropboxService;

    public FileServiceImpl(FileRepository fileRepository, ModelMapper modelMapper, DropboxService dropboxService) {
        this.fileRepository = fileRepository;
        this.modelMapper = modelMapper;
        this.dropboxService = dropboxService;
    }

    @Override
    public List<FileViewModel> getAllFiles() {
        return fileRepository.findAll()
                .stream()
                .map(file ->{
                    FileViewModel fileViewModel = modelMapper.map(file, FileViewModel.class);
                    fileViewModel.setCommentsCounter(file.getComments().size());
                    return fileViewModel;
                }).collect(Collectors.toList());
    }

    @Override
    public void addNewFile(AddFileDTO addFileDTO) {
        MultipartFile dataFile = addFileDTO.dataFile();

        String dataFileUrl = dropboxService.testMyUpload(dataFile);



    }

}
