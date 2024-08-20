package org.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PhoneNumberResponseDto {
    private Long id;
    private String numberDto;
    @JsonProperty("user")
    private UserResponseDto userResponseDto;

    public PhoneNumberResponseDto() {}

    public PhoneNumberResponseDto(String numberDto, UserResponseDto userResponseDto) {
        this.numberDto = numberDto;
        this.userResponseDto = userResponseDto;
    }

    public Long getId() { return id; }
    public void setId(Long id) {this.id = id;}
    public String getNumberDto() { return numberDto; }
    public void setNumberDto(String numberDto) { this.numberDto = numberDto; }
    public UserResponseDto getUserDto() { return userResponseDto; }
    public void setUserDto(UserResponseDto userResponseDto) { this.userResponseDto = userResponseDto; }//

}

