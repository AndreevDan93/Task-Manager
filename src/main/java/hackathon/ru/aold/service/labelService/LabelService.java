package hackathon.ru.service.labelService;

import hackathon.ru.dto.LabelDto;
import hackathon.ru.model.Label;

import java.util.List;

public interface LabelService {
    Label getLabelById(long id);
    List<Label> getAllLabels();
    Label createLabel(LabelDto labelDTO);
    Label updateLabel(long id, LabelDto labelDTO);
    void deleteLabelById(long id);

}
