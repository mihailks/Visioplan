package com.visioplanserver.model.entity;

import com.visioplanserver.model.entity.enums.DrawindTypeEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
public class DrawingEntity extends FileEntity{
    @Enumerated(EnumType.STRING)
    @Column(name = "drawing_type", nullable = false)
    private DrawindTypeEnum type;

    public DrawingEntity() {
    }

    public DrawindTypeEnum getType() {
        return type;
    }

    public DrawingEntity setType(DrawindTypeEnum type) {
        this.type = type;
        return this;
    }
}
