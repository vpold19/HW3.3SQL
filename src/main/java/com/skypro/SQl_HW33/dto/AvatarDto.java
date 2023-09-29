package com.skypro.SQl_HW33.dto;

import com.skypro.SQl_HW33.model.Avatar;

import java.util.Objects;

public class AvatarDto {
    private long id;
    private long studentId;
    private String name;
    public AvatarDto(){

    }
public static AvatarDto fromEntity(Avatar entity){
        AvatarDto dto = new AvatarDto();
        dto.setId(entity.getId());
        dto.setStudentId(entity.getStudent().getId());
        dto.setName(entity.getStudent().getName());
        return dto;
}
    public AvatarDto(long id, long studentId, String name) {
        this.id = id;
        this.studentId = studentId;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AvatarDto avatarDto = (AvatarDto) o;
        return id == avatarDto.id && studentId == avatarDto.studentId && Objects.equals(name, avatarDto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, studentId, name);
    }
}
