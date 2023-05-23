package hackathon.ru.service.statusService;

import hackathon.ru.dto.ApplicationStatusDto;
import hackathon.ru.model.ApplicationStatus;

import java.util.List;

public interface StatusService {
    ApplicationStatus getStatusById(Long id);
    List<ApplicationStatus> getAllStatus();
    ApplicationStatus createStatus(ApplicationStatusDto applicationStatusDto);
    ApplicationStatusDto updateStatus(Long id, ApplicationStatusDto applicationStatusDto);
    void deleteStatus(Long id);
}
