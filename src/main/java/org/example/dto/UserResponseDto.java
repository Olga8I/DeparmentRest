package org.example.dto;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private RoleResponseDto roleResponseDto;
    private List<PhoneNumberResponseDto> phoneNumberResponseDtoList;
    private Set<DepartmentCreateDto> departmentCreateDtoList;

    public UserResponseDto() {
        this.phoneNumberResponseDtoList = new ArrayList<>();
        this.departmentCreateDtoList = new HashSet<>();
    }

    public UserResponseDto(String firstName, String lastName, RoleResponseDto roleResponseDto, List<PhoneNumberResponseDto> phoneNumberList,
                           Set<DepartmentCreateDto> departmentList) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.roleResponseDto = roleResponseDto;
        this.phoneNumberResponseDtoList = phoneNumberList;
        this.departmentCreateDtoList = departmentList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public RoleResponseDto getRoleDto() {
        return roleResponseDto;
    }

    public List<PhoneNumberResponseDto> getPhoneNumberList() {
        return phoneNumberResponseDtoList;
    }

    public Set<DepartmentCreateDto> getDepartmentList() {
        return departmentCreateDtoList;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setRoleDto(RoleResponseDto roleResponseDto) {
        this.roleResponseDto = roleResponseDto;
    }

    public void setPhoneNumberList(List<PhoneNumberResponseDto> phoneNumberList) {
        this.phoneNumberResponseDtoList = phoneNumberList;
    }

    public void setDepartmentList(Set<DepartmentCreateDto> departmentList) {
        this.departmentCreateDtoList = departmentList;
    }
}
