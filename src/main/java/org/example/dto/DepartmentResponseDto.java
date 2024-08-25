package org.example.dto;

import java.util.List;

public class DepartmentResponseDto {

    private Long id;
    private String name;
    private List<UserResponseDto> userList;

    public DepartmentResponseDto() {}

    public DepartmentResponseDto(Long id, String name, List<UserResponseDto> userListDto) {
        this.id = id;
        this.name = name;
        this.userList = userListDto;
    }

    public Long getId() { return id; }
    public void setId(Long id) {this.id = id;}
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<UserResponseDto> getUserList() { return userList; }
    public void setUserList(List<UserResponseDto> userList) { this.userList = userList; }// передаю userUpdate
}
