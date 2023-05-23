package hackathon.ru.service.vacancyService;

import hackathon.ru.dto.VacancyDto;
import hackathon.ru.model.Vacancy;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class VacancyServiceImpl implements VacancyService{
    @Override
    public Vacancy getVacancyById(Long id) {
        return null;
    }

    @Override
    public List<Vacancy> getAllVacancies() {
        return null;
    }

    @Override
    public Vacancy createVacancy(VacancyDto VacancyDto) {
        return null;
    }

    @Override
    public Vacancy updateVacancy(Long id, VacancyDto vacancyDto) {
        return null;
    }

    @Override
    public void deleteVacancy(Long id) {

    }
}
