package hackathon.ru.dto;

import hackathon.ru.model.ApplicationStatus;
import hackathon.ru.model.Candidate;
import hackathon.ru.model.Vacancy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationDto {

    private String comment;

    @NotNull(message = "vacancyId should not be empty")
    private Long vacancyId;

    @NotNull(message = "candidateId should not be empty")
    private Long candidateId;

    @NotNull(message = "candidateId should not be empty")
    private Long applicationStatusId;

    // статус назначается автоматически как новая.

}

