package hackathon.ru.exception.custom;

import javax.persistence.EntityNotFoundException;

public class VacancyStatusNotFoundException extends EntityNotFoundException {
    public VacancyStatusNotFoundException(String msg) {
        super(msg);
    }
}
