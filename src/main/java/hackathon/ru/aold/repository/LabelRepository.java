package hackathon.ru.aold.repository;

import hackathon.ru.aold.model.Label;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LabelRepository extends JpaRepository<Label, Long> {
}
