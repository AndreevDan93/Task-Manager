package hackathon.ru.service.applicationService;

import hackathon.ru.dto.ApplicationDto;
import hackathon.ru.dto.UserDto;
import hackathon.ru.model.Application;

import java.util.List;

public interface ApplicationService {
    Application getApplicationById(Long id);
    List<Application> getAllApplications();
    Application createApplication(ApplicationDto applicationDto);
    Application updateApplication(Long id, ApplicationDto applicationDto);
    void deleteApplication(Long id);
}
