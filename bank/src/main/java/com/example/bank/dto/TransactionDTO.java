package com.example.bank.dto;

import com.example.bank.dto.enums.CardAccountEnum;
import lombok.Data;

@Data
public class TransactionDTO {
    private int money;
    private String fromCardOrAccount;
    private String fromNumber;
    private String fromName;
    private boolean toMeOrNotToMe;
    private String toCardOrAccount;
    private String toNumber;
    private String toName;
    private String toAnotherNumber;
}
