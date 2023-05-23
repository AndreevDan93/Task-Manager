package hackathon.ru.exception.custom;

import javax.persistence.EntityNotFoundException;

public class StatusNotFoundException extends EntityNotFoundException {
    public StatusNotFoundException(String msg) {
        super(msg);
    }
}
