package Pack01.service;

import Pack01.domain.UserRanking;
import Pack01.repository.UserRankingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRankingService {
    private final UserRankingRepository userRankingRepository;

    @Autowired
    public UserRankingService(UserRankingRepository userRankingRepository) {
        this.userRankingRepository = userRankingRepository;
    }

    public List<UserRanking> findUserRankingList(){
        return userRankingRepository.findUserRankingList();
    }

    public UserRanking findUserRankingById(Long user_id){
        return userRankingRepository.findUserRankingById(user_id);
    }
}
