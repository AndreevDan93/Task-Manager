package hackathon.ru.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cities")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "userCity")
    private List<User> users;

    @OneToMany(mappedBy = "vacancyCity")
    private List<Vacancy> vacancies;
}
