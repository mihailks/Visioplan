package com.visioplanserver.model.entity;

import com.visioplanserver.model.entity.enums.BuldingDocumentationPartEnum;
import com.visioplanserver.model.entity.enums.FileExtensionEnum;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;


//@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Table(name = "files")
public class FileEntity extends BaseEntity {
    @Column(name = "file_name", nullable = false, unique = true)
    private String name;
    @Column(name = "file_url", nullable = false, unique = true)
    private String url;
    @Column(name = "upload_date", nullable = false)
    private LocalDateTime uploadDate;
    @ManyToOne
    private FloorEntity floor;
    @OneToMany
    private Set<CommentsEntity> comments;
    @Enumerated(EnumType.STRING)
    @Column(name = "file_extension", nullable = false)
    private FileExtensionEnum extension;
    @Enumerated(EnumType.STRING)
    @Column(name = "design_part", nullable = false)
    private BuldingDocumentationPartEnum part;

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

    public Set<CommentsEntity> getComments() {
        return comments;
    }

    public FileEntity setComments(Set<CommentsEntity> comments) {
        this.comments = comments;
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
}
