package hackathon.ru.service.iService;

import hackathon.ru.dto.ApplicationStatusDto;
import hackathon.ru.model.ApplicationStatus;

import java.util.List;

public interface ApplicationStatusService {
    ApplicationStatus getApplicationStatusById(Long id);
    List<ApplicationStatus> getAllApplicationStatuses();
    ApplicationStatus createApplicationStatus(ApplicationStatusDto applicationStatusDto);
    ApplicationStatusDto updateApplicationStatus(Long id, ApplicationStatusDto applicationStatusDto);
    void deleteApplicationStatusById(Long id);
}
