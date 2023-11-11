package com.visioplanserver.model.view;

import com.visioplanserver.model.entity.CommentsEntity;
import com.visioplanserver.model.entity.FloorEntity;
import com.visioplanserver.model.entity.enums.BuldingDocumentationPartEnum;
import com.visioplanserver.model.entity.enums.FileExtensionEnum;

import java.time.LocalDateTime;
import java.util.Set;

public class FileViewModel {
    private Long id;
    private String name;
    private String url;
    private LocalDateTime uploadDate;
    private String floor;
    private Integer commentsCounter;
    private FileExtensionEnum extension;
    private BuldingDocumentationPartEnum part;

    public FileViewModel() {
    }

    public FileViewModel(String name, String url, LocalDateTime uploadDate, String floor, Integer commentsCounter, FileExtensionEnum extension, BuldingDocumentationPartEnum part) {
        this.name = name;
        this.url = url;
        this.uploadDate = uploadDate;
        this.floor = floor;
        this.commentsCounter = commentsCounter;
        this.extension = extension;
        this.part = part;
    }

    public String getName() {
        return name;
    }

    public FileViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public FileViewModel setUrl(String url) {
        this.url = url;
        return this;
    }

    public LocalDateTime getUploadDate() {
        return uploadDate;
    }

    public FileViewModel setUploadDate(LocalDateTime uploadDate) {
        this.uploadDate = uploadDate;
        return this;
    }

    public String getFloor() {
        return floor;
    }

    public FileViewModel setFloor(String floor) {
        this.floor = floor;
        return this;
    }

    public Integer getCommentsCounter() {
        return commentsCounter;
    }

    public FileViewModel setCommentsCounter(Integer commentsCounter) {
        this.commentsCounter = commentsCounter;
        return this;
    }

    public FileExtensionEnum getExtension() {
        return extension;
    }

    public FileViewModel setExtension(FileExtensionEnum extension) {
        this.extension = extension;
        return this;
    }

    public BuldingDocumentationPartEnum getPart() {
        return part;
    }

    public FileViewModel setPart(BuldingDocumentationPartEnum part) {
        this.part = part;
        return this;
    }

    public Long getId() {
        return id;
    }

    public FileViewModel setId(Long id) {
        this.id = id;
        return this;
    }
}
