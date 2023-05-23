package hackathon.ru.aold.exception.custom;

import javax.persistence.EntityNotFoundException;

public class LabelNotFoundException extends EntityNotFoundException {
    public LabelNotFoundException(String message) {
        super(message);
    }
}
