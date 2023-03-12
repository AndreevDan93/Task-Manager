package hexlet.code.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskStatusDTO {
    @NotBlank(message = "Task Status Name should not be Empty")
    @Column(name = "name")
    private String name;
}
