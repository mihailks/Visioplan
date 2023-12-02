package com.visioplanserver.model.dto;

import com.visioplanserver.model.entity.enums.BuldingDocumentationPartEnum;
import com.visioplanserver.model.entity.enums.DrawingTypeEnum;
import com.visioplanserver.model.entity.enums.FileExtensionEnum;
import com.visioplanserver.model.entity.enums.TextFileTypeEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;


public record AddFileDTO(
        @NotNull
        @Size(min = 2, max = 250, message = "Please choose a building!")
        String buildingName,
        @NotNull
        String floor,
        @NotNull(message = "Please choose a part of the building!")
        BuldingDocumentationPartEnum part,
        DrawingTypeEnum drawingType,
        TextFileTypeEnum textFileType,
        @NotBlank(message = "Please enter a name!")
        @Size(min = 2, max = 250, message = "Name must be between 2 and 40 characters!")
        String name,
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
