package com.bruno_pimenta_dev.to_do_list.business.service;

import com.bruno_pimenta_dev.to_do_list.infraestructure.repository.ProfileRepository;
import com.bruno_pimenta_dev.to_do_list.infraestructure.dto.ProfileRequestDTO;
import com.bruno_pimenta_dev.to_do_list.infraestructure.entity.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class ProfileService {
    @Autowired
    private ProfileRepository repository;

    public boolean saveProfile(ProfileRequestDTO dto){
        Profile profile = Profile.dtoToProfile(dto);
        Profile profileSaved = repository.save(profile);
        if(profileSaved!=null){
            return true;
        }return false;
    }

    public List<Profile> getAllProfiles(){
        return repository.findAll();
    }

    public boolean deleteProfile(Integer id){
        repository.deleteById(id);
        Profile profile = repository.findById(id).orElse(null);
        if(profile!=null){
            return false;
        }return true;
    }
}
