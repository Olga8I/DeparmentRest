package org.example.dto;

import java.util.Objects;

public class RoleDto {
    private Long id;
    private String name;

    public RoleDto() {
    }

    public RoleDto(String name) {
        this.name = name;

    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {this.id = id; }
    public String getName() {
        return name;
    }
    public void setName(String name) {this.name = name;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoleDto roleDto)) return false;
        return Objects.equals(id, roleDto.id) && Objects.equals(name, roleDto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "RoleDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

