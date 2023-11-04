package com.visioplanserver.service.impl;

import com.visioplanserver.model.view.FileViewModel;
import com.visioplanserver.repository.FileRepository;
import com.visioplanserver.service.FileService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileServiceImpl implements FileService {
    private final FileRepository fileRepository;
    private final ModelMapper modelMapper;

    public FileServiceImpl(FileRepository fileRepository, ModelMapper modelMapper) {
        this.fileRepository = fileRepository;
        this.modelMapper = modelMapper;
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

}
