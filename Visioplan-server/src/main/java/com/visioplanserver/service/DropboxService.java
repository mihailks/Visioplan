package com.visioplanserver.service;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.FolderMetadata;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface DropboxService {


    FolderMetadata getFolderDetails(String folderPath);
    FileMetadata getFileDetails(String filePath);
    FileMetadata uploadFile(String filePath, InputStream fileStream);

    String getAccountDetails() throws DbxException;

    String getFileNames() throws DbxException;

    String upload(MultipartFile dataFile);

}
