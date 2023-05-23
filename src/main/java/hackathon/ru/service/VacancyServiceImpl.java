package hackathon.ru.service;

import hackathon.ru.dto.VacancyDto;
import hackathon.ru.exception.custom.VacancyNotFoundException;
import hackathon.ru.model.Vacancy;
import hackathon.ru.repository.VacancyRepository;
import hackathon.ru.service.iService.VacancyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class VacancyServiceImpl implements VacancyService {

    private final VacancyRepository vacancyRepository;

    @Override
    public Vacancy getVacancyById(Long id) {
        return vacancyRepository.findById(id)
                .orElseThrow(() -> new VacancyNotFoundException("vacancy with that id is not found"));
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
    public void deleteVacancyById(Long id) {

    }
}
