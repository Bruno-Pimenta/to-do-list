package com.bruno_pimenta_dev.to_do_list.infraestructure.entity;

import com.bruno_pimenta_dev.to_do_list.infraestructure.dto.ProfileRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "e_mail", nullable = false, unique = true)
    private String email;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks;

    public Profile(String userName, String email, String passwordHash) {
        this.userName = userName;
        this.email = email;
        this.passwordHash = passwordHash;
        this.createdAt = Instant.now();
        this.tasks = new ArrayList<Task>();
    }

    public Profile() {
    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public static Profile dtoToProfile(ProfileRequestDTO dto){
        return new Profile(dto.getUserName(), dto.getEmail(), dto.getPassword());
    }

}
