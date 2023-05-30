package ru.project.Investart.forms;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.project.Investart.entity.DevTeam;
import ru.project.Investart.entity.Startup;

import java.util.Date;

@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Service
public class StartupReq {
    @NonNull
    private String name;

    @NonNull
    private String description;

    @NonNull
    private double needMoney;

    @NonNull
    private Date endDate;

    @NonNull
    private Long author_id;

}
