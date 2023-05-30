package ru.project.Investart.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Service
public class UserLogin {
    @NonNull
    private String username;

    @NonNull
    private String password;
}
