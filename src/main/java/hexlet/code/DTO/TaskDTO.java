package hexlet.code.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {
    @NotBlank(message = "Name of Task should not be Empty")
    private String name;

    private String description;

    private Long executorId;

    @NotNull(message = "Task Status should not be Empty")
    private Long taskStatusId;
}
