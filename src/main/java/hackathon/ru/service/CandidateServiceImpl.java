package hackathon.ru.service.candidateService;

import hackathon.ru.dto.CandidateDto;
import hackathon.ru.model.Candidate;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CandidateServiceImpl implements CandidateService{

    @Override
    public Candidate getCandidateById(Long id) {
        return null;
    }

    @Override
    public List<Candidate> getCandidates() {
        return null;
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
