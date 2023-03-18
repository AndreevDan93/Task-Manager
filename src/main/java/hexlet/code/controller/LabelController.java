package hexlet.code.controller;

import hexlet.code.dto.LabelDto;
import hexlet.code.model.Label;
import hexlet.code.service.labelService.LabelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static hexlet.code.controller.LabelController.LABEL_CONTROLLER_PATH;

@Tag(name = "Label controller")
@RestController
@RequestMapping("${base-url}" + LABEL_CONTROLLER_PATH)
@AllArgsConstructor
public class LabelController {
    public static final String LABEL_CONTROLLER_PATH = "/labels";
    public static final String ID = "/{id}";
    private final LabelService labelService;

    @Operation(summary = "Get Label by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Label with that id was successfully found"),
            @ApiResponse(responseCode = "404", description = "Label with that id does not exist")
    })
    @GetMapping(ID)
    public Label getLabelById(@PathVariable("id") final long id) {
        return labelService.getLabelById(id);
    }

    @Operation(summary = "Get all labels")
    @ApiResponse(responseCode = "200", description = "Labels was successfully found")
    @GetMapping
    public List<Label> getAllLabels() {
        return labelService.getAllLabels();
    }

    @Operation(summary = "Create new Label")
    @ApiResponse(responseCode = "201", description = "Label was successfully created")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public Label createLabel(@RequestBody @Valid final LabelDto labelDTO) {
        return labelService.createLabel(labelDTO);
    }

    @Operation(summary = "Update Label by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Label with that id was successfully updated"),
            @ApiResponse(responseCode = "404", description = "Label with that id does not exist")
    })
    @PutMapping(ID)
    public Label updateLabel(@PathVariable("id") final long id,
                             @RequestBody @Valid final LabelDto labelDTO) {
        return labelService.updateLabel(id, labelDTO);
    }

    @Operation(summary = "Delete Label by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Label with that id was successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Label with that id does not exist")
    })
    @DeleteMapping(ID)
    public void deleteLabelById(@PathVariable("id") final long id) {
        labelService.deleteLabelById(id);
    }
}
