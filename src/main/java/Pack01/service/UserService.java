package Pack01.service;

import Pack01.domain.User;
import Pack01.repository.dto.SolvedProblemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Pack01.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //로그인
    /**
     * @return null이면 로그인 실패
     */
    public User login(String loginId, String password) {
        Optional<User> userOptional = userRepository.findById(loginId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    // 회원가입
    public void save(User user) {
        userRepository.save(user);
    }

    // userId로 조회
    public User findByUserId(Long userId) {
        return userRepository.findByUserId(userId);
    }


    // 내가 맞은 문제 리스트
    public List<SolvedProblemDto> selectSolvedProblem(Long userId){
        return userRepository.selectSolvedProblem(userId);
    }



    // 유저 랭킹

}
