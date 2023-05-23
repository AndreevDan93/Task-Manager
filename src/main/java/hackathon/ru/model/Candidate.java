package hackathon.ru.model;

import lombok.*;
import org.yaml.snakeyaml.events.Event;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "candidates")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "expected_salary")
    private String expectedSalary;

    @Lob
    @Column(name = "description")
    private String description;

    @NotBlank(message = "Birthday should not be Empty")
    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "resume_url")
    private String resumeUrl;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "candidate")
    private List<Application> applications;
}
