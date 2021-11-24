package com.example.bank.repositories;

import com.example.bank.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepo extends JpaRepository<Card, Long> {
    Card findCardByNumber(String number);
}
