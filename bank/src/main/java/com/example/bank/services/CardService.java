package com.example.bank.services;

import com.example.bank.models.Card;
import com.example.bank.repositories.CardRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepo cardRepo;

    public Card findCardByNumber(String number) {
        return cardRepo.findCardByNumber(number);
    }

}
