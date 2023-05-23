package hackathon.ru.service;

import hackathon.ru.dto.CandidateDto;
import hackathon.ru.exception.custom.CandidateNotFoundException;
import hackathon.ru.model.Candidate;
import hackathon.ru.repository.CandidateRepository;
import hackathon.ru.service.iService.CandidateService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CandidateServiceImpl implements CandidateService {

    private final CandidateRepository candidateRepository;

    @Override
    public Candidate getCandidateById(Long id) {
        return candidateRepository.findById(id)
                .orElseThrow(() -> new CandidateNotFoundException("candidate with that id is not found"));
    }

    @Override
    public List<Candidate> getCandidates() {
        return new ArrayList<>(candidateRepository.findAll());
    }

    @Override
    public Candidate createCandidate(CandidateDto candidateDto) {
        return null;
    }

    @Override
    public Candidate updateCandidate(Long id, CandidateDto candidateDto) {
        return null;
    }

    @Override
    public void deleteCandidate(Long id) {

    }
}
