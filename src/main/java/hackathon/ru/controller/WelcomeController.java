package hackathon.ru.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Welcome controller")
@RestController("/welcome")
public class WelcomeController {
    @Operation(summary = "Welcome page")
    @ApiResponse(responseCode = "200", description = "Welcome page has been loaded")
    @GetMapping()
    public String welcome() {
        return "Welcome to Spring";
    }
}
