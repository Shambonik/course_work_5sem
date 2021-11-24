package com.example.bank.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "client_details")
@Getter
@Setter
public class ClientDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String email;
    private String firstname;
    private String lastname;
    private String middlename;
    private String passportNumber;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private Set<Card> cards;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private Set<Account> accounts;

}
