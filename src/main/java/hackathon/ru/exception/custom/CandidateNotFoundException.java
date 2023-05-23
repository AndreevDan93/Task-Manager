package hackathon.ru.exception.custom;

import javax.persistence.EntityNotFoundException;

public class CandidateNotFoundException extends EntityNotFoundException {
    public CandidateNotFoundException(String message) {
        super(message);
    }
}
