package com.spring_boot_pet.config;

import com.spring_boot_pet.model.UserEntity;
import com.spring_boot_pet.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // Выбор вида шифрования паролей
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                // Настройка правил авторизации HTTP-запросов
                .authorizeHttpRequests(auth -> auth
                        // Эти пути доступны всем без входа в систему
                        .requestMatchers("/registration", "/css/**", "/js/**").permitAll()
                        // Любой другой запрос требует аутентификации
                        .anyRequest().authenticated()
                )
                // Настройка входа через форму
                .formLogin(form -> form
                        // Своя страница логина по адресу /login
                        .loginPage("/login")
                        // Куда перенаправить после успешного входа (true — всегда на "/")
                        .defaultSuccessUrl("/", true)
                        // Страница логина доступна всем
                        .permitAll()
                )
                // Настройка выхода из системы
                .logout(logout -> logout
                        // Куда перенаправить после выхода
                        .logoutSuccessUrl("/login")
                        // Эндпоинт выхода доступен всем
                        .permitAll()
                )
                // Собираем и возвращаем цепочку фильтров
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepo) {
        return username -> {
            UserEntity user = userRepo.findByUsername(username);
            if (user != null) return user;
            throw new UsernameNotFoundException("User ‘" + username + "’ not found");
        };
    }
}
