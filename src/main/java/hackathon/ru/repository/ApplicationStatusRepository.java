package hackathon.ru.repository;

import hackathon.ru.model.ApplicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<ApplicationStatus, Long> {
}
