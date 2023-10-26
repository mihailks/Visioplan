package com.visioplanserver.entity;

import com.visioplanserver.entity.enums.BuldingDocumentationPartEnum;
import com.visioplanserver.entity.enums.DrawindTypeEnum;
import com.visioplanserver.entity.enums.FileExtensionEnum;
import jakarta.persistence.*;

import java.util.Set;


//@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Table(name = "files")
public class FileEntity extends BaseEntity{
    private String name;
    private String url;
    @ManyToOne
    private FloorEntity floor;
    @OneToMany
    private Set<CommentsEntity> comments;
    @Enumerated(EnumType.STRING)
    private FileExtensionEnum extension;
    @Enumerated(EnumType.STRING)
    private BuldingDocumentationPartEnum part;

}
