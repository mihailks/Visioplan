package com.visioplanserver.entity;

import com.visioplanserver.entity.enums.DrawindTypeEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
public class DrawingEntity extends FileEntity{
    @Enumerated(EnumType.STRING)
    private DrawindTypeEnum type;
}
