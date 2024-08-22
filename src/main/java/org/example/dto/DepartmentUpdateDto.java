package org.example.dto;

public class DepartmentUpdateDto {
    private String name;
    private Long id;
    public DepartmentUpdateDto() {}

   public DepartmentUpdateDto(Long id, String name) {
        this.id = id;
        this.name = name;
   }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
