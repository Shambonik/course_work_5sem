package com.example.bank.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash(value = "TempRegistration", timeToLive = 1800)
@Getter
@Setter
public class TempRegistration implements Serializable {
    @Id
    private String token;
    private String cardNumber;
    private String password;
}
