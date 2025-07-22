package com.bruno_pimenta_dev.to_do_list.infraestructure.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ProfileRequestDTO {
    @NotBlank(message = "O nome de usuário é obrigatório")
    @Size(max = 100, message  = "O nome de usuário deve ter no máximo 100 caracteres")
    private String userName;

    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "E-mail inválido")
    @Size(max = 100, message = "O e-mail deve ter no máximo 100 caracteres")
    private String email;

    @NotBlank(message = "A senha é obrigatória")
    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
    private String password;

}
