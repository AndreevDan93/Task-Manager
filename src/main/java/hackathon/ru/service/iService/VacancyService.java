package hackathon.ru.service.vacancyService;

import hackathon.ru.dto.ApplicationDto;
import hackathon.ru.dto.VacancyDto;
import hackathon.ru.model.Application;
import hackathon.ru.model.Vacancy;

import java.util.List;

public interface VacancyService {
    Vacancy getVacancyById(Long id);
    List<Vacancy> getAllVacancies();
    Vacancy createVacancy(VacancyDto VacancyDto);
    Vacancy updateVacancy(Long id, VacancyDto vacancyDto);
    void deleteVacancy(Long id);
}
