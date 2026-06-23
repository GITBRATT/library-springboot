package com.spring_boot_pet.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class RegistrationForm {
    @NotBlank(message = "Имя пользователя обязательно")
    private String username;

    @NotBlank(message = "Пароль обязателен")
    @Size(min = 5, message = "Пароль должен быть не короче 5 символов")
    private String password;

    //@NotBlank(message = "Полное имя обязательно")
    private String fullname;

    //@NotBlank(message = "Город обязателен")
    private String city;

    public UserEntity toUser(PasswordEncoder passwordEncoder) {
        return new UserEntity(
                username, passwordEncoder.encode(password),
                fullname, city);
    }
}