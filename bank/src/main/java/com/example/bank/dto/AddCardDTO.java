package com.example.bank.dto;

import lombok.Data;

import java.util.Date;

@Data
public class AddCardDTO {
    private String number;
    private String validDate;
    private Integer cvv;
}
