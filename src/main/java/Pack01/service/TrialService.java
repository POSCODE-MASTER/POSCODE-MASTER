package Pack01.service;

import Pack01.domain.Trial;
import Pack01.repository.TrialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TrialService {

    private final TrialRepository trialRepository;

    @Autowired
    public TrialService(TrialRepository trialRepository) {
        this.trialRepository = trialRepository;
    }

    public Trial save(Trial trial) {
        return trialRepository.save(trial);
    }

    public String findCodeByUserIdProblemIdSolveTime(long trialId){
        return trialRepository.findCodeByUserIdProblemIdSolveTime(trialId);
    }
}
