package hackathon.ru.exception.custom;

import javax.persistence.EntityNotFoundException;

public class ApplicationStatusNotFoundException extends EntityNotFoundException {
    public ApplicationStatusNotFoundException(String message) {
        super(message);
    }
}
