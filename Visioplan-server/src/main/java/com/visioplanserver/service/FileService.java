package com.visioplanserver.service;

import com.visioplanserver.model.entity.FileEntity;
import com.visioplanserver.model.view.FileViewModel;

import java.util.List;

public interface FileService {

    List<FileViewModel> getAllFiles();

}
