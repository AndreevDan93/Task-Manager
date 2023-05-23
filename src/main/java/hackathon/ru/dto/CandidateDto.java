package hackathon.ru.dto;

import hackathon.ru.model.Application;
import hackathon.ru.model.City;
import hackathon.ru.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CandidateDto {

    private String expectedSalary;

    private String description;

    private Date birthday;

    private String resumeUrl;

    private Long userId;

    private List<Long> applicationsIds;
}
