package com.visioplanserver.service.impl;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.*;
import com.dropbox.core.v2.sharing.SharedLinkMetadata;
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
    public String upload(MultipartFile dataFile) {
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
            String pathLower = client
                    .files()
                    .uploadBuilder("/" + dataFile.getOriginalFilename())
                    .uploadAndFinish(in)
                    .getPreviewUrl();

            SharedLinkMetadata meta = client.sharing().createSharedLinkWithSettings("/" + dataFile.getOriginalFilename());

            String url = meta.getUrl();
            url = url.split("\\?")[0];
//            url = url + "\\?raw=1";
            return url;
        } catch (IOException | DbxException e) {
            throw new RuntimeException(e);
        }
    }

}
