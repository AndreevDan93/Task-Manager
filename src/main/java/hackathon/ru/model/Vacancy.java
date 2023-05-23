package hackathon.ru.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "vacancies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vacancy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;
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
    private City city;
    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;





}
