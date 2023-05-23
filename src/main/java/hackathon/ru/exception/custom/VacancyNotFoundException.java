package hackathon.ru.exception.custom;

import javax.persistence.EntityNotFoundException;

public class VacancyNotFoundException extends EntityNotFoundException {
    public VacancyNotFoundException(String msg) {
        super(msg);
    }
}
