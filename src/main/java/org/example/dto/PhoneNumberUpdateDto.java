package org.example.dto;

public class PhoneNumberUpdateDto {
    private Long id;
    private String number;

    public PhoneNumberUpdateDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public PhoneNumberUpdateDto(Long id, String number) {
        this.id = id;
        this.number = number;
    }
}
