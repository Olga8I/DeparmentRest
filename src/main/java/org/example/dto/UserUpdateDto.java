package org.example.dto;

public class UserUpdateDto {
    private String firstName;
    private String lastName;
    private RoleResponseDto roleResponseDto;
    private Long id;
    public UserUpdateDto() {
    }

    public UserUpdateDto(String firstName, String lastName, RoleResponseDto roleResponseDto, Long id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.roleResponseDto = roleResponseDto;
        this.id = id;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
