package hackathon.ru.exception.custom;

import javax.persistence.EntityNotFoundException;

public class RoleNotFoundException extends EntityNotFoundException {
    public RoleNotFoundException(String msg) {
        super(msg);
    }
}
