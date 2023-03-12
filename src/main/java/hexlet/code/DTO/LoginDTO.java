package hexlet.code.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {
    @NotBlank(message = "Email should not be Empty")
    @Email(message = "Incorrect Email")
    @Column(name = "email", unique = true)
    private String email;

    @NotBlank(message = "Password should not be Empty")
    @Size(min = 3, max = 100, message = "Password should be between at 3 to 100 symbols")
    @Column(name = "password")
    private String password;
}
