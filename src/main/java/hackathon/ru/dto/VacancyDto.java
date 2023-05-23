package hackathon.ru.dto;

import hackathon.ru.model.Application;
import hackathon.ru.model.City;
import hackathon.ru.model.User;
import hackathon.ru.model.VacancyStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VacancyDto {

    private String name;

    private String experience;

    private String minSalary;

    private String maxSalary;

    private String publicSalary;

    private String description;

    private String requirement;

    private String responsibility;

    private String benefit;


    private Long cityId;

    private Long userId;

    private Long vacancyStatusId;

    private List<Long> applicationsId;

// Статус вакансии и автор вакансии заполняются автоматически(статус новая, автор текущий пользователь)
}
