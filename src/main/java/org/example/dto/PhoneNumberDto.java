package org.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class PhoneNumberDto {
    private Long id;
    private String numberDto;
    @JsonProperty("user")
    private UserDto userDto;

    public PhoneNumberDto() {}

    public PhoneNumberDto(String numberDto, UserDto userDto) {
        this.numberDto = numberDto;
        this.userDto = userDto;
    }

    public Long getId() { return id; }
    public void setId(Long id) {this.id = id;}
    public String getNumberDto() { return numberDto; }
    public void setNumberDto(String numberDto) { this.numberDto = numberDto; }
    public UserDto getUserDto() { return userDto; }
    public void setUserDto(UserDto userDto) { this.userDto = userDto; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PhoneNumberDto that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(numberDto, that.numberDto) && Objects.equals(userDto, that.userDto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numberDto);
    }

    @Override
    public String toString() {
        return "PhoneNumberDto{" +
                "id=" + id +
                ", number='" + numberDto + '\'' +
                ", userDto=" + userDto +
                '}';
    }
}

