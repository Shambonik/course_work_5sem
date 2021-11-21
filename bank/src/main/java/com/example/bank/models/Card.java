package com.example.bank.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cards")
@Data
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String number;
    private String cardHolder;
    private Date validDate;
    private Integer cvv;

    @ManyToOne
    private ClientDetails client;
}
