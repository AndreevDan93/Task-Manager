package hackathon.ru.exception.custom;

import javax.persistence.EntityNotFoundException;

public class ApplicationNotFoundException extends EntityNotFoundException {
    public ApplicationNotFoundException(String message) {
        super(message);
    }
}
