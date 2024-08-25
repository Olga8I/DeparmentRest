package org.example.dto;

public class DepartmentCreateDto {
    private String name;

    public DepartmentCreateDto() {
    }

    public DepartmentCreateDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
