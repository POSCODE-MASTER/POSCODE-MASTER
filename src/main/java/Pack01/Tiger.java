package Pack01;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Tiger {
	
	@RequestMapping("/t1")
	public String func01() {
		System.out.println("func01 call");
		return "TigerView";//클래스가 타이거라서?
	}

//	@RequestMapping("/login")
//	public String login(){
//		return "login";
//	}

	@RequestMapping("header")
	public String header(){
		return "header";
	}

	@RequestMapping("/")
	public String index(){return "index";}

	@RequestMapping("/problemList")
	public String problemList(){return "problemList";}

	@RequestMapping("/solve")
	public String solve(){return "solve";}


	@RequestMapping("/problemBoard")
	public String problemBoard(){return "problemBoard";}

	@RequestMapping("/problemBoardList")
	public String problemBoardList(){return "problemBoardList";}

	@RequestMapping("/ranking")
	public String ranking(){return "ranking";}

	@RequestMapping("/problemCreate")
	public String problemCreate(){return "problemCreate";}
}
