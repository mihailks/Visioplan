package com.visioplanserver.service;

import com.visioplanserver.model.dto.AddFileDTO;
import com.visioplanserver.model.view.FileViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FileService {

    List<FileViewModel> getAllFiles();
    Page<FileViewModel> getAllFiles(Pageable pageable);
    Page<FileViewModel> findPage(int pageNumber);

    void addNewFile(AddFileDTO addFileDTO);

    void deleteFile(Long id);
}
