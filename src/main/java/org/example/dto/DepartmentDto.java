package org.example.dto;

import java.util.List;
import java.util.Objects;

public class DepartmentDto {
    private Long id;
    private String name;
    private List<UserDto> userListDto;

    public DepartmentDto() {}

    public DepartmentDto(String name, List<UserDto> userListDto) {
        this.name = name;
        this.userListDto = userListDto;
    }

    public Long getId() { return id; }
    public void setId(Long id) {this.id = id;}
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<UserDto> getUserList() { return userListDto; }
    public void setUserList(List<UserDto> userList) { this.userListDto = userListDto; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DepartmentDto that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "DepartmentDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userList=" + userListDto +
                '}';
    }
}
