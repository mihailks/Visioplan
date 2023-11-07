package com.visioplanserver.model.dto;

import com.visioplanserver.model.entity.CommentsEntity;
import com.visioplanserver.model.entity.FloorEntity;
import com.visioplanserver.model.entity.enums.BuldingDocumentationPartEnum;
import com.visioplanserver.model.entity.enums.DrawingTypeEnum;
import com.visioplanserver.model.entity.enums.FileExtensionEnum;
import com.visioplanserver.model.entity.enums.TextFileTypeEnum;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Set;

public record AddFileDTO(

        String buildingName,

        String floor,

        BuldingDocumentationPartEnum part,

        DrawingTypeEnum drawingType,

        TextFileTypeEnum textFileType,

        String name,

        FileExtensionEnum extension,

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
