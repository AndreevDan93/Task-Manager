package hackathon.ru.exception.custom;

import javax.persistence.EntityNotFoundException;

public class CityNotFoundException extends EntityNotFoundException {
    public CityNotFoundException(String message) {
        super(message);
    }
}
