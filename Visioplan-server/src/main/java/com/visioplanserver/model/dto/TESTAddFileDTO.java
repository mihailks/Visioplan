package com.visioplanserver.model.dto;

import com.visioplanserver.model.entity.CommentsEntity;
import com.visioplanserver.model.entity.FloorEntity;
import com.visioplanserver.model.entity.enums.BuldingDocumentationPartEnum;
import com.visioplanserver.model.entity.enums.FileExtensionEnum;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Set;

public record TESTAddFileDTO(

        String name,

        String url,
        MultipartFile dataFile) {

    public static TESTAddFileDTO createEmpty() {
        return new TESTAddFileDTO(
                null,
                null,
                null);
    }

}
