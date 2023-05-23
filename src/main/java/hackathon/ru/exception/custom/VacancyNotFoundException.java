package hackathon.ru.exception.custom;

import javax.persistence.EntityNotFoundException;

public class EmployeeNotFoundException extends EntityNotFoundException {
    public EmployeeNotFoundException(String msg) {
        super(msg);
    }
}
