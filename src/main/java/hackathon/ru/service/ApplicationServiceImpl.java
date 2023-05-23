package hackathon.ru.service;

import hackathon.ru.dto.ApplicationDto;
import hackathon.ru.exception.custom.ApplicationNotFoundException;
import hackathon.ru.model.Application;
import hackathon.ru.model.Candidate;
import hackathon.ru.model.ApplicationStatus;
import hackathon.ru.model.Vacancy;
import hackathon.ru.repository.ApplicationRepository;
import hackathon.ru.service.iService.ApplicationService;
import hackathon.ru.service.iService.CandidateService;
import hackathon.ru.service.iService.ApplicationStatusService;
import hackathon.ru.service.iService.VacancyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final CandidateService candidateService;
    private final VacancyService vacancyService;
    private final ApplicationStatusService applicationStatusService;

    @Override
    public Application getApplicationById(Long id) {
        return applicationRepository.findById(id)
                .orElseThrow(() -> new ApplicationNotFoundException("application with that id is not found"));
    }

    @Override
    public List<Application> getAllApplications() {
        return new ArrayList<>(applicationRepository.findAll());
    }

    @Override
    public Application createApplication(ApplicationDto applicationDto) {
        Application application = Application.builder()
                .comment(applicationDto.getComment())
                .vacancy(vacancyService.getVacancyById(applicationDto.getVacancyId()))
                .candidate(candidateService.getCandidateById(applicationDto.getCandidateId()))
                .status(applicationStatusService.getApplicationStatusById(applicationDto.getApplicationStatusId()))
                .build();
        return applicationRepository.save(application);
    }

    @Override
    public Application updateApplication(Long id, ApplicationDto applicationDto) {
        Application application = getApplicationById(id);
        Vacancy vacancy = vacancyService.getVacancyById(applicationDto.getVacancyId());
        Candidate candidate = candidateService.getCandidateById(applicationDto.getVacancyId());
        ApplicationStatus status = applicationStatusService.getApplicationStatusById(applicationDto.getApplicationStatusId());
        application.setComment(application.getComment());
        application.setVacancy(vacancy);
        application.setCandidate(candidate);
        application.setStatus(status);
        return applicationRepository.save(application);
    }

    @Override
    public void deleteApplicationById(Long id) {
        Application application = getApplicationById(id);
        applicationRepository.delete(application);
    }
}
