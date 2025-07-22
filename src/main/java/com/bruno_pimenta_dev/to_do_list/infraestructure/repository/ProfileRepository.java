package com.bruno_pimenta_dev.to_do_list.infraestructure.repository;


import com.bruno_pimenta_dev.to_do_list.infraestructure.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer> {
}
