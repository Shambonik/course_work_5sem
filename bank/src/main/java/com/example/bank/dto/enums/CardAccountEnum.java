package com.example.bank.dto.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CardAccountEnum {
    CARD("card", "Карта"),
    ACCOUNT("account", "Счет");

    private String key;
    private String value;
}
