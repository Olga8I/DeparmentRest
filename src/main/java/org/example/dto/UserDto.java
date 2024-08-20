package org.example.dto;


import java.util.List;
import java.util.Objects;
import java.util.Set;

public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private RoleDto roleDto;
    private List<PhoneNumberDto> phoneNumberDtoList;
    private Set<DepartmentDto> departmentDtoList;

    public UserDto() {
    }

    public UserDto(String firstName, String lastName, RoleDto roleDto, List<PhoneNumberDto> phoneNumberList,
                   Set<DepartmentDto> departmentList) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.roleDto =  roleDto;
        this.phoneNumberDtoList = phoneNumberList;
        this.departmentDtoList = departmentList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {this.id = id;}

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public RoleDto getRoleDto() {
        return roleDto;
    }

    public List<PhoneNumberDto> getPhoneNumberList() {
        return phoneNumberDtoList;
    }

    public Set<DepartmentDto> getDepartmentList() {
        return departmentDtoList;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setRoleDto(RoleDto roleDto) {
        this.roleDto = roleDto;
    }

    public void setPhoneNumberList(List<PhoneNumberDto> phoneNumberList) {
        this.phoneNumberDtoList = phoneNumberList;
    }

    public void setDepartmentList(Set<DepartmentDto> departmentList) {
        this.departmentDtoList = departmentList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDto userDto)) return false;
        return Objects.equals(id, userDto.id) && Objects.equals(firstName, userDto.firstName) && Objects.equals(lastName, userDto.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName);
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", roleDto=" + roleDto +
                ", phoneNumberList=" + phoneNumberDtoList +
                ", departmentList=" + departmentDtoList +
                '}';
    }
}



