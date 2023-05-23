package hackathon.ru.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    @NotBlank(message = "First Name should not be Empty")
    private String firstName;

    @NotBlank(message = "Last Name should not be Empty")
    private String lastName;

    @NotBlank(message = "Email should not be Empty")
    @Email(message = "Incorrect Email")
    private String email;

    @NotBlank(message = "Password should not be Empty")
    @Size(min = 3, max = 100, message = "Password should be between at 3 to 100 symbols")
    private String password;
}
