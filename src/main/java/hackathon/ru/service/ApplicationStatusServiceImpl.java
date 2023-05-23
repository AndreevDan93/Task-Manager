package hackathon.ru.service.statusService;

import hackathon.ru.dto.ApplicationStatusDto;
import hackathon.ru.exception.custom.VacancyStatusNotFoundException;
import hackathon.ru.model.ApplicationStatus;
import hackathon.ru.repository.ApplicationStatusRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StatusServiceImpl implements StatusService{
    private final ApplicationStatusRepository applicationStatusRepository;
    @Override
    public ApplicationStatus getStatusById(Long id) {
        return applicationStatusRepository.findById(id)
                .orElseThrow(() -> new VacancyStatusNotFoundException("status with that id is not found"));
    }

    @Override
    public List<ApplicationStatus> getAllStatus() {
        return null;
    }

    @Override
    public ApplicationStatus createStatus(ApplicationStatusDto applicationStatusDto) {
        return null;
    }

    @Override
    public ApplicationStatusDto updateStatus(Long id, ApplicationStatusDto applicationStatusDto) {
        return null;
    }

    @Override
    public void deleteStatus(Long id) {

    }
}
