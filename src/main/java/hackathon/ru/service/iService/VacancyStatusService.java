package hackathon.ru.service.iService;

import hackathon.ru.dto.VacancyDto;
import hackathon.ru.model.Vacancy;

import java.util.List;

public interface VacancyStatusService {
    Vacancy getVacancyStatusById(Long id);

    List<Vacancy> getAllVacancyStatuses();

    Vacancy createVacancyStatus(VacancyDto VacancyDto);

    Vacancy updateVacancyStatus(Long id, VacancyDto vacancyDto);

    void deleteVacancyStatusById(Long id);
}
