package hackathon.ru.aold.service.labelService;

import hackathon.ru.aold.dto.LabelDto;
import hackathon.ru.aold.model.Label;

import java.util.List;

public interface LabelService {
    Label getLabelById(long id);
    List<Label> getAllLabels();
    Label createLabel(LabelDto labelDTO);
    Label updateLabel(long id, LabelDto labelDTO);
    void deleteLabelById(long id);

}
