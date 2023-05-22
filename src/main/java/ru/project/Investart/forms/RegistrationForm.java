package ru.project.Investart.forms;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.project.Investart.entity.Role;
import ru.project.Investart.entity.User;


@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationForm {

    @NonNull
    private String username;

    @NonNull
    private String password;
    @NonNull
    private String email;


    @NonNull
    private String phoneNumber;


    @NonNull
    private Role role;


    public User toUser(PasswordEncoder passwordEncoder) {
        return new User(username,email,phoneNumber,passwordEncoder.encode(password),role);
    }
}