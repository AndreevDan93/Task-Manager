package hexlet.code.service.labelService;

import hexlet.code.dto.LabelDto;
import hexlet.code.model.Label;

import java.util.List;

public interface LabelService {
    Label getLabelById(long id);
    List<Label> getAllLabels();
    Label createLabel(LabelDto labelDTO);
    Label updateLabel(long id, LabelDto labelDTO);
    void deleteLabelById(long id);

}
