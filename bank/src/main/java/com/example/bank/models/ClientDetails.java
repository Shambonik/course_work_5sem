package com.example.bank.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "client_details")
@Data
public class ClientDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstname;
    private String lastname;
    private String middlename;
    private String passportNumber;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private Set<Card> cards;

}
