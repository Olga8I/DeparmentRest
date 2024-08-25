package org.example.dto;

public class UserCreateDto {
    private String firstName;
    private String lastName;
    private RoleResponseDto roleResponseDto;

    public UserCreateDto() {
    }

    public UserCreateDto(String firstName, String lastName, RoleResponseDto roleResponseDto) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.roleResponseDto = roleResponseDto;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public RoleResponseDto getRoleDto() {
        return roleResponseDto;
    }

    public void setRoleDto(RoleResponseDto roleResponseDto) {
        this.roleResponseDto = roleResponseDto;
    }
}
