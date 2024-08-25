package org.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

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

    @Override
    public String toString() {
        return "PhoneNumberResponseDto{" +
                "id=" + id +
                ", numberDto='" + numberDto + '\'' +
                ", userResponseDto=" + userResponseDto +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneNumberResponseDto that = (PhoneNumberResponseDto) o;
        return Objects.equals(id, that.id) && Objects.equals(numberDto, that.numberDto) && Objects.equals(userResponseDto, that.userResponseDto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numberDto, userResponseDto);
    }
}

