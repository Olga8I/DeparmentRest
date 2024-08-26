package org.example.dto;
import java.util.*;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserResponseDto)) return false;
        UserResponseDto that = (UserResponseDto) o;
        return Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }
}
