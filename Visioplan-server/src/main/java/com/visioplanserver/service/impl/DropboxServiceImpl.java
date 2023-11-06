package com.visioplanserver.service.impl;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.*;
import com.dropbox.core.v2.users.FullAccount;
import com.visioplanserver.service.DropboxActionResolver;
import com.visioplanserver.service.DropboxService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
class DropboxServiceImpl implements DropboxService {

    private final DbxClientV2 client;

    public DropboxServiceImpl(DbxClientV2 client) {
        this.client = client;
    }


    @Override
    public FolderMetadata getFolderDetails(String folderPath) {
        return getMetadata(folderPath, FolderMetadata.class, String.format("Error getting folder details: %s", folderPath));
    }

    @Override
    public FileMetadata getFileDetails(String filePath) {
        return getMetadata(filePath, FileMetadata.class, String.format("Error getting file details: %s", filePath));
    }

    @Override
    public FileMetadata uploadFile(String filePath, InputStream fileStream) {
        return handleDropboxAction(() -> client.files().uploadBuilder(filePath).uploadAndFinish(fileStream),
                String.format("Error uploading file: %s", filePath));
    }

    @Override
    public String getAccountDetails() throws DbxException {
        FullAccount account = client.users().getCurrentAccount();
        System.out.println(account.getName().getDisplayName());
        return account.getName().getDisplayName();
    }

    @Override
    public String getFileNames() throws DbxException {
        ListFolderResult result = client.files().listFolder("");
        while (true) {
            for (Metadata metadata : result.getEntries()) {
                System.out.println(metadata.getPathDisplay());
                System.out.println(metadata.getPreviewUrl());
                System.out.println(metadata.getPathLower());
                System.out.println(metadata.getName());
            }

            if (!result.getHasMore()) {
                break;
            }

            result = client.files().listFolderContinue(result.getCursor());
        }
        return null;
    }

    @Override
    public String testMyUpload(MultipartFile dataFile) {
        File file;

        try {
            file = File.createTempFile("temp_dataFile", dataFile.getOriginalFilename());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try{
            dataFile.transferTo(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (InputStream in = new FileInputStream(file)) {
            return client
                    .files()
                    .uploadBuilder("/" + dataFile.getOriginalFilename())
                    .uploadAndFinish(in)
                    .toString();
        } catch (IOException | DbxException e) {
            throw new RuntimeException(e);
        }
    }

    private <T> T handleDropboxAction(DropboxActionResolver<T> action, String exceptionMessage) {
        try {
            return action.perform();
        } catch (Exception e) {
            String messageWithCause = String.format("%s with cause: %s", exceptionMessage, e.getMessage());
            throw new RuntimeException();
        }
    }

    @SuppressWarnings("unchecked")
    private <T> T getMetadata(String path, Class<T> type, String message) {
        Metadata metadata = handleDropboxAction(() -> client.files().getMetadata(path),
                String.format("Error accessing details of: %s", path));

        checkIfMetadataIsInstanceOfGivenType(metadata, type, message);
        return (T) metadata;
    }

    private <T> void checkIfMetadataIsInstanceOfGivenType(Metadata metadata, Class<T> validType, String exceptionMessage) {
        boolean isValidType = validType.isInstance(metadata);
        if (!isValidType) {
            throw new RuntimeException();
        }
    }
}
