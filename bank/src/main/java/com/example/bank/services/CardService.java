package com.example.bank.services;

import com.example.bank.dto.TopUpDTO;
import com.example.bank.models.Card;
import com.example.bank.models.ClientDetails;
import com.example.bank.repositories.CardRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepo cardRepo;

    public Card findCardByNumber(String number) {
        return cardRepo.findCardByNumber(number);
    }

    public Card findCardById(Long id) {
        return cardRepo.findCardById(id);
    }

    public void topUpCard(Long id, TopUpDTO dto){
        Card card = findCardById(id);
        card.setMoney(card.getMoney()+dto.getMoney());
        cardRepo.save(card);
    }
}
