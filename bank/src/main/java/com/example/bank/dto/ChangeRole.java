package com.example.bank.dto;

import com.example.bank.models.enums.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChangeRole {
    Role role;
}
