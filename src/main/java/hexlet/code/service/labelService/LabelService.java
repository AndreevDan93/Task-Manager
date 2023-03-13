package hexlet.code.service.labelService;

import hexlet.code.DTO.LabelDTO;
import hexlet.code.model.Label;

import java.util.List;

public interface LabelService {
    Label getLabelById(long id);
    List<Label> getAllLabels();
    Label createLabel(LabelDTO labelDTO);
    Label updateLabel(long id, LabelDTO labelDTO);
    void deleteLabelById(long id);

}
