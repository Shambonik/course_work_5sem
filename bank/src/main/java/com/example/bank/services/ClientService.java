package com.example.bank.services;

import com.example.bank.dto.ClientListDTO;
import com.example.bank.dto.FindByPassportNumberDTO;
import com.example.bank.models.ClientDetails;
import com.example.bank.repositories.ClientRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepo clientRepo;

    public List<ClientListDTO> getClientList(){
        return clientListToDTO(clientRepo.findAll());
    }

    public String filterByPassportNumber(FindByPassportNumberDTO findByPassportNumberDTO, RedirectAttributes redirectAttributes){
        String passportNumber = findByPassportNumberDTO.getPassportNumber().replace(" ", "");
        if(!"".equals(passportNumber)) {
            redirectAttributes.addFlashAttribute("list", clientListToDTO(clientRepo.findByPassportNumber(passportNumber)));
            redirectAttributes.addFlashAttribute("findByPassportNumber", findByPassportNumberDTO);
        }
        return "redirect:/admin/clients";
    }

    private List<ClientListDTO> clientListToDTO(List<ClientDetails> clientDetails){
        return clientDetails.stream()
                .map((client) -> {
                    ClientListDTO dto = new ClientListDTO();
                    StringBuilder fullName = new StringBuilder();
                    fullName.append(client.getFirstname())
                            .append(" ")
                            .append(client.getLastname());
                    if(client.getMiddlename() != null){
                        fullName.append(" ")
                                .append(client.getMiddlename());
                    }
                    dto.setFullName(fullName.toString());
                    dto.setPassportNumber(client.getPassportNumber());
                    dto.setId(client.getId());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
