package com.example.bank.dto.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum CardAccountEnum {
    CARD(1, "Карта"),
    ACCOUNT(2, "Счет");

    private int key;
    private String value;
}
