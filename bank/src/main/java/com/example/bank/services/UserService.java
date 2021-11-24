package com.example.bank.services;

import com.example.bank.models.Card;
import com.example.bank.models.ClientDetails;
import com.example.bank.models.TempRegistration;
import com.example.bank.models.User;
import com.example.bank.models.enums.Role;
import com.example.bank.repositories.UserRepo;
import com.example.bank.repositories.redis.TempRegistrationRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final CardService cardService;
    private final TempRegistrationRepo tempRegistrationRepo;
    private final EmailService emailService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public String registration(TempRegistration temp, Map<String, Object> model) {
        Card card = cardService.findCardByNumber(temp.getCardNumber());
        if (card == null) {
            model.put("message", "Карта не найдена");
            return "registration";
        }
        ClientDetails client = card.getClient();
        User user = userRepo.findByUsername(client.getEmail());
        if (user != null) {
            model.put("message", "Пользователь существует");
            return "registration";
        }
        TempRegistration tempByCard = tempRegistrationRepo.findTempRegistrationByCardNumber(temp.getCardNumber());
        if(tempByCard != null){
            tempRegistrationRepo.delete(temp);
        }
        TempRegistration reg = new TempRegistration();
        reg.setToken(UUID.randomUUID().toString());
        reg.setCardNumber(temp.getCardNumber());
        reg.setPassword(passwordEncoder.encode(temp.getPassword()));
        tempRegistrationRepo.save(reg);
        emailService.send(client.getEmail(),
                "Подтверждение регистрации",
                client.getLastname() + " " +
                        client.getFirstname() +
                        ", для подтверждения регистрации перейдите по ссылке\n" +
                        "http://localhost:8080/registration/approve/" + reg.getToken());
        return "redirect:/email_sent";
    }

    public String approveRegistration(String token) {
        Optional<TempRegistration> tempOptional = tempRegistrationRepo.findById(token);
        if (tempOptional.isEmpty()) {
            return "redirect:/token_not_found";
        }
        TempRegistration temp = tempOptional.get();
        User user = new User();
        user.setRoles(Collections.singleton(Role.USER));
        user.setPassword(temp.getPassword());
        ClientDetails client = cardService.findCardByNumber(temp.getCardNumber()).getClient();
        user.setUsername(client.getEmail());
        user.setClientDetails(client);
        user.setActive(true);
        userRepo.save(user);
        tempRegistrationRepo.delete(temp);
        return "redirect:/login";
    }
}
