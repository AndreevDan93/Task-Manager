package hackathon.ru.service;

import hackathon.ru.dto.ApplicationStatusDto;
import hackathon.ru.exception.custom.VacancyStatusNotFoundException;
import hackathon.ru.model.ApplicationStatus;
import hackathon.ru.repository.ApplicationStatusRepository;
import hackathon.ru.service.iService.ApplicationStatusService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ApplicationStatusServiceImpl implements ApplicationStatusService {
    private final ApplicationStatusRepository applicationStatusRepository;
    @Override
    public ApplicationStatus getApplicationStatusById(Long id) {
        return applicationStatusRepository.findById(id)
                .orElseThrow(() -> new VacancyStatusNotFoundException("status with that id is not found"));
    }

    @Override
    public List<ApplicationStatus> getAllApplicationStatuses() {
        return null;
    }

    @Override
    public ApplicationStatus createApplicationStatus(ApplicationStatusDto applicationStatusDto) {
        return null;
    }

    @Override
    public ApplicationStatusDto updateApplicationStatus(Long id, ApplicationStatusDto applicationStatusDto) {
        return null;
    }

    @Override
    public void deleteApplicationStatusById(Long id) {

    }
}
