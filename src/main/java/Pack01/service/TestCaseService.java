package Pack01.service;

import Pack01.domain.Testcase;
import Pack01.repository.TestcaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestCaseService {

    private final TestcaseRepository testcaseRepository;

    @Autowired
    public TestCaseService(TestcaseRepository testcaseRepository) {
        this.testcaseRepository = testcaseRepository;
    }

    //testcase 저장
    public Testcase save(Testcase testcase) {
        return testcaseRepository.save(testcase);
    }

    // problem_id로 testcase 리스트 조회
    public List<Testcase> findByProblemId(Long problemId) {
        return testcaseRepository.findByProblemId(problemId);
    }

    //testcase 업데이트
    public void update(Long updateTestcaseId, Testcase testcase) {
        testcaseRepository.updateTestcase(updateTestcaseId, testcase);
    }
}
