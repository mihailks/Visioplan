package com.visioplanserver.model.entity;

import com.visioplanserver.model.entity.enums.BuldingDocumentationPartEnum;
import com.visioplanserver.model.entity.enums.DrawingTypeEnum;
import com.visioplanserver.model.entity.enums.FileExtensionEnum;
import com.visioplanserver.model.entity.enums.TextFileTypeEnum;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "files")
public class FileEntity extends BaseEntity {
    @Column(name = "file_name", nullable = false, unique = true)
    private String name;
    @Column(name = "file_url", nullable = false, unique = true)
    private String url;
    @Column(name = "upload_date", nullable = false)
    private LocalDateTime uploadDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "drawing_type", nullable = false)
    private DrawingTypeEnum drawingType;
    @Enumerated(EnumType.STRING)
    @Column(name = "TextFile_type", nullable = false)
    private TextFileTypeEnum textFileType;
    @Enumerated(EnumType.STRING)
    @Column(name = "file_extension", nullable = false)
    private FileExtensionEnum extension;
    @Enumerated(EnumType.STRING)
    @Column(name = "design_part", nullable = false)
    private BuldingDocumentationPartEnum part;
    @ManyToOne
    private FloorEntity floor;
    @OneToMany(mappedBy = "file", fetch = FetchType.EAGER)
    private Set<CommentsEntity> comments;

    public FileEntity() {
    }

    public String getName() {
        return name;
    }

    public FileEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public FileEntity setUrl(String url) {
        this.url = url;
        return this;
    }

    public FloorEntity getFloor() {
        return floor;
    }

    public FileEntity setFloor(FloorEntity floor) {
        this.floor = floor;
        return this;
    }


    public FileExtensionEnum getExtension() {
        return extension;
    }

    public FileEntity setExtension(FileExtensionEnum extension) {
        this.extension = extension;
        return this;
    }

    public BuldingDocumentationPartEnum getPart() {
        return part;
    }

    public FileEntity setPart(BuldingDocumentationPartEnum part) {
        this.part = part;
        return this;
    }

    public LocalDateTime getUploadDate() {
        return uploadDate;
    }

    public FileEntity setUploadDate(LocalDateTime uploadDate) {
        this.uploadDate = uploadDate;
        return this;
    }

    public DrawingTypeEnum getDrawingType() {
        return drawingType;
    }

    public FileEntity setDrawingType(DrawingTypeEnum drawingType) {
        this.drawingType = drawingType;
        return this;
    }

    public TextFileTypeEnum getTextFileType() {
        return textFileType;
    }

    public FileEntity setTextFileType(TextFileTypeEnum textFileType) {
        this.textFileType = textFileType;
        return this;
    }

    public Set<CommentsEntity> getComments() {
        return comments;
    }

    public FileEntity setComments(Set<CommentsEntity> comments) {
        this.comments = comments;
        return this;
    }
}
