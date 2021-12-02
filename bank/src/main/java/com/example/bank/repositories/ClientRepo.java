package com.example.bank.repositories;

import com.example.bank.models.ClientDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepo extends JpaRepository<ClientDetails, Long> {
    ClientDetails findClientDetailsById(Long id);

    List<ClientDetails> findByPassportNumber(String passportNumber);
}
