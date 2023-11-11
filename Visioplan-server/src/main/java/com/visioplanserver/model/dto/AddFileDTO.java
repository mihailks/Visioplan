package com.visioplanserver.model.dto;

import com.visioplanserver.model.entity.CommentsEntity;
import com.visioplanserver.model.entity.FloorEntity;
import com.visioplanserver.model.entity.enums.BuldingDocumentationPartEnum;
import com.visioplanserver.model.entity.enums.DrawingTypeEnum;
import com.visioplanserver.model.entity.enums.FileExtensionEnum;
import com.visioplanserver.model.entity.enums.TextFileTypeEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Set;

public record AddFileDTO(
        @NotNull
        @Size(min = 2, max = 250, message = "Building name must be between 2 and 40 characters!")
        String buildingName,
        @NotNull
        String floor,
        @NotNull
        BuldingDocumentationPartEnum part,
        @NotNull
        DrawingTypeEnum drawingType,
        @NotNull
        TextFileTypeEnum textFileType,
        @NotNull
        @Size(min = 2, max = 250, message = "Name must be between 2 and 40 characters!")
        String name,
        @NotNull
        FileExtensionEnum extension,
        @NotNull
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
