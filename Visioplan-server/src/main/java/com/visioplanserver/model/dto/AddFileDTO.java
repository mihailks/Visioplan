package com.visioplanserver.model.dto;

import com.visioplanserver.model.entity.CommentsEntity;
import com.visioplanserver.model.entity.FloorEntity;
import com.visioplanserver.model.entity.enums.BuldingDocumentationPartEnum;
import com.visioplanserver.model.entity.enums.FileExtensionEnum;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Set;

public record AddFileDTO(

        String name,

        String url,

        LocalDateTime uploadDate,

        FloorEntity floor,

        Set<CommentsEntity> comments,

        FileExtensionEnum extension,

        BuldingDocumentationPartEnum part,
        MultipartFile dataFile) {

    public static AddFileDTO createEmpty() {
        return new AddFileDTO(
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null);
    }

}
