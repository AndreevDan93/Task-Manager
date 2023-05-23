package hackathon.ru.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "statuses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApplicationStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany
    @JoinColumn(name = "application_id", referencedColumnName = "id")
    private List<Application> applications;
}
