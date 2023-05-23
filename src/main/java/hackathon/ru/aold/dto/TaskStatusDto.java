package hackathon.ru.aold.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskStatusDto {
    @NotBlank(message = "Task Status Name should not be Empty")
    private String name;
}
