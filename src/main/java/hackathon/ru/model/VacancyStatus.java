package hackathon.ru.model;

import liquibase.pro.packaged.I;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "vacancy_status")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VacancyStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "vacancyStatus")
    private List<Vacancy> vacancies;
}
