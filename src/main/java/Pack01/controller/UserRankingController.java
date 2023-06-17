package Pack01.controller;

import Pack01.domain.User;
import Pack01.domain.UserRanking;
import Pack01.service.UserRankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@Controller
public class UserRankingController {
    private final UserRankingService userRankingService;
    @Autowired
    public UserRankingController(UserRankingService userRankingService) {
        this.userRankingService = userRankingService;
    }

    @RequestMapping(value = "/ranking", method = RequestMethod.GET)
    public String userRanking(@SessionAttribute(name = "loginUser", required = false) User loginUser,Model model){
        List<UserRanking> userRankingList = userRankingService.findUserRankingList();
        model.addAttribute("rankingList",userRankingList);

        long userId = loginUser.getUserId();
        UserRanking myRanking = userRankingService.findUserRankingById(userId);
        model.addAttribute("myRanking",myRanking);

        return "ranking";
    }

}
