package hexlet.code.controller;

import hexlet.code.DTO.LabelDTO;
import hexlet.code.model.Label;
import hexlet.code.service.labelService.LabelService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static hexlet.code.controller.LabelController.LABELS_CONTROLLER_PATH;

@RestController
@RequestMapping("${base-url}" + LABELS_CONTROLLER_PATH)
@AllArgsConstructor
public class LabelController {
    public static final String LABELS_CONTROLLER_PATH = "/labels";
    public static final String ID = "/{id}";
    private final LabelService labelService;

    @GetMapping(ID)
    public Label getLabelById(@PathVariable("id") final long id) {
        return labelService.getLabelById(id);
    }

    @GetMapping
    public List<Label> getAllLabels() {
        return labelService.getAllLabels();
    }

    @PostMapping()
    public Label createLabel(@RequestBody @Valid final LabelDTO labelDTO) {
        return labelService.createLabel(labelDTO);
    }

    @PutMapping(ID)
    public Label updateLabel(@PathVariable("id") final long id,
                             @RequestBody @Valid final LabelDTO labelDTO) {
        return labelService.updateLabel(id, labelDTO);
    }

    @DeleteMapping(ID)
    public void deleteLabelById(@PathVariable("id") final long id) {
        labelService.deleteLabelById(id);
    }
}
