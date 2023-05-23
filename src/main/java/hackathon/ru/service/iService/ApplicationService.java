package hackathon.ru.service.iService;

import hackathon.ru.dto.ApplicationDto;
import hackathon.ru.model.Application;

import java.util.List;

public interface ApplicationService {
    Application getApplicationById(Long id);
    List<Application> getAllApplications();
    Application createApplication(ApplicationDto applicationDto);
    Application updateApplication(Long id, ApplicationDto applicationDto);
    void deleteApplicationById(Long id);
}
