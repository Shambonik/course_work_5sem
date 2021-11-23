package com.example.bank.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "cards")
@Data
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String number;
    private String cardHolder;
    private LocalDate validDate;
    private Integer cvv;

    @ManyToOne
    private ClientDetails client;
}
