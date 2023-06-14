package Pack01.service;

import Pack01.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Pack01.repository.UserRepository;

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
//    public User login(String loginId, String password) {
//        User findMemberOptional = userRepository.findById(loginId);
//        User member = findMemberOptional.get();
//        if(member.getPassword().equals(password)) {
//            return member;
//        } else {
//            return null;
//        }
//    }

    // 회원가입
    public void save(User user) {
        userRepository.save(user);
    }
}
