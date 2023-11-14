package com.visioplanserver.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface DropboxService {

    String upload(MultipartFile dataFile);

}
