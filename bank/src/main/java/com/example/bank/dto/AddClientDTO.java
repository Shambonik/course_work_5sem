package com.example.bank.dto;


import lombok.Data;

@Data
public class AddClientDTO {
    private String firstname;
    private String lastname;
    private String middlename;
    private String passportNumber;
}
