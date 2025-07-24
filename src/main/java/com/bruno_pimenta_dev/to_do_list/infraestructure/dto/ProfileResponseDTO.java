package com.bruno_pimenta_dev.to_do_list.infraestructure.dto;

import com.bruno_pimenta_dev.to_do_list.infraestructure.entity.Profile;
import java.util.List;

public class ProfileResponseDTO {
    private Long id;
    private String userName;
    private List<TaskResponseDTO> tasks;

    public ProfileResponseDTO(Long id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public static ProfileResponseDTO profileToDto(Profile profile){
        return new ProfileResponseDTO(profile.getId(), profile.getUserName());
    }
}
