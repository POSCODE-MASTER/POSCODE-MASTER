package Pack01.service;

import Pack01.controller.form.ProblemForm;
import Pack01.domain.Problem;
import Pack01.repository.ProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProblemService {

    private final ProblemRepository problemRepository;

    @Autowired
    public ProblemService(ProblemRepository problemRepository) {
        this.problemRepository = problemRepository;
    }

    //problem 저장
    public Problem save(Long userId, ProblemForm problemForm) {

        LocalDateTime now = LocalDateTime.now();
        Problem problem = new Problem(userId, problemForm.getTitle(), problemForm.getDescription(), now, now, problemForm.getLevel());

        return problemRepository.save(problem);
    }


    // userId로 problem 리스트 출력 (자신이 출제한 problem들 출력)
    public List<Problem> findByUserId(Long userId) {
        List<Problem> problems = problemRepository.findByUserId(userId);

        return problems;
    }

    // problem 수정
    public void updateProblem(Long updateProblemId, Problem problem) {
        problemRepository.updateProblem(updateProblemId, problem);
    }


    //전체 조회 (페이징 + level 필터)
    public List<Problem> selectAll(int page, String level) {
        return problemRepository.selectAll(page, level);
    }
}
