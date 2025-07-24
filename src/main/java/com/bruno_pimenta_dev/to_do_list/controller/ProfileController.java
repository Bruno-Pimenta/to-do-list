package com.bruno_pimenta_dev.to_do_list.controller;

import com.bruno_pimenta_dev.to_do_list.business.service.ProfileService;
import com.bruno_pimenta_dev.to_do_list.infraestructure.dto.ProfileRequestDTO;
import com.bruno_pimenta_dev.to_do_list.infraestructure.dto.ProfileResponseDTO;
import com.bruno_pimenta_dev.to_do_list.infraestructure.entity.Profile;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/profiles")
public class ProfileController {
    @Autowired
    private ProfileService service;

    @PostMapping
    public String saveProfile(@Valid @RequestBody ProfileRequestDTO dto){
        boolean returnSave = service.saveProfile(dto);
        if(returnSave){
            return "Perfil salvo com sucesso!";
        }return "Falha ao salvar perfil";
    }

    @GetMapping
    public List<ProfileResponseDTO> getAllProfiles(){
        List<ProfileResponseDTO> profilesDto = new ArrayList<>();
        List<Profile> profiles = service.getAllProfiles();
        profiles.forEach(profile ->{
            profilesDto.add(ProfileResponseDTO.profileToDto(profile));
        });
        return profilesDto;
    }

    @DeleteMapping("/{user-id}")
    public String deleteProfile(@PathVariable("user-id") Long userId){
        boolean returnDelete = service.deleteProfile(userId);
        if(returnDelete){
            return "Perfil apagado com sucesso!";
        }return "Falha ao apagar perfil";
    }


}
