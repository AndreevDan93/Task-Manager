package hexlet.code.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name of Task should not be Empty")
    @Column(name = "name")
    private String name;

    @Lob
    @Column(name = "description")
    private String description;

    @NotNull(message = "Task Status should not be Empty")
    @ManyToOne
    @JoinColumn(name = "task_status_id", referencedColumnName = "id")
    private TaskStatus taskStatus;

    @NotNull(message = "Author should not be Empty")
    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private User author;

    @ManyToOne
    @JoinColumn(name = "executor_id", referencedColumnName = "id")
    private User executor;

    @CreationTimestamp
    @Temporal(TIMESTAMP)
    @Column(name = "createdAt")
    private Date createdAt;


}
