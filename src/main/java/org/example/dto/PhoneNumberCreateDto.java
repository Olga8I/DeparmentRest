package org.example.dto;

public class PhoneNumberCreateDto {

    public PhoneNumberCreateDto() {
    }

    private String number;

    public PhoneNumberCreateDto(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
