package hackathon.ru.model;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "vacancies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vacancy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "experience")
    private String experience;

    @Column(name = "min_salary")
    private String minSalary;

    @Column(name = "max_salary")
    private String maxSalary;

    @Column(name = "public_salary")
    private String publicSalary;

    @Column(name = "description")
    private String description;

    @Column(name = "requirement")
    private String requirement;

    @Column(name = "responsibility")
    private String responsibility;

    @Column(name = "benefit")
    private String benefit;

    @ManyToOne
    @JoinColumn(name = "city_id", referencedColumnName = "id")
    private City vacancyCity;

    @ManyToOne
    @JoinColumn(name = "city_id", referencedColumnName = "id")
    private User author;

    @ManyToOne()
    @JoinColumn(name = "vacancy_status_id", referencedColumnName = "id")
    private VacancyStatus vacancyStatus;

    @OneToMany(mappedBy = "vacancy")
    private List<Application> applications;
}
