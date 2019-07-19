package com.kitri.member.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.kitri.member.HomeController;
import com.kitri.member.model.MemberDetailDto;
import com.kitri.member.model.MemberDto;
import com.kitri.member.model.service.MemberService;

//@SessionAttributes(names = {'a','b','c'}) // 배열
@Controller
@RequestMapping("/user")
@SessionAttributes("userInfo") // 배열도 가능함. 세션에 담긴 것이 아니라 스프링에서 제공해주는 세션을 사용 그러므로 스프링 세션을 지워야뎀.
public class MemberController{ 

//	로고 띄워주는 역할
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
//	@Qualifier() // implement를 여러개 하는 애라면 퀄리파이어를 사용하야뎀
	@Autowired // servlet-context에서 controller에 dao,service를 주입하는게 없는데 찾을 수 있게 하는 방법
	private MemberService memberService;
	
	
	// 가져가는 데이터가 있으면 model,view 없으면 view만
	// 원래 url는 /user/mvjoin.kitri인데 위에 맵핑을 /user 해놔서 지움. 
	// register를 두개로 만들어 놨지만 get과 post로 판별함.
	// value = "{asdasd, qweqwe}" 이런식으로 배열로 판별 가능함.
	@RequestMapping(value = "/register.kitri" , method = RequestMethod.GET) 
	public String register() {
		return "user/member/member"; // view의 이름
	}
	
	@RequestMapping(value = "/register.kitri" , method = RequestMethod.POST) 
	public String register(MemberDetailDto memberDetailDto, Model model) {  // Model model = map으로 써도 됨.
		// form의 속성 이름과 dto이름이 같으면 알아서 다 넣어줌. 하지만 아니면 (@RequestParam(name = "doro" , defaultValue = "") 12개 만들어야뎀.
		// register(HttpServletRequest request, MemberDetailDto memberDetailDto, Model model) 가능하지만 그전에 MemberDetailDto 에 집어넣고 시작함
		// 다 넣어놓고 끝나는것이라 컨트롤러 오기전에 filter로 고쳐줘야함.
		int cnt = memberService.registerMember(memberDetailDto);
		if (cnt != 0) {
			model.addAttribute("registerInfo",memberDetailDto); // model을 직접적으로 설정할때 사용
			return "user/member/registerok"; // view
		}
		return "user/member/registerfail"; 
	}
	
	@RequestMapping(value = "/idcheck.kitri") // method = RequestMethod.GET 는 하나밖에 없으므로 알아서 판별.
	// @ResponeseBody는 받아오는게 제이슨형태(몸통)이다 라는 뜻
	public @ResponseBody String idCheck(@RequestParam(name = "checkid" , defaultValue = "")String id) { 
		// request.getparameter을 안써도 알아서 들어감. 변수의 이름하고 parameter값하고 같아야뎀. 그런데 변수 이름을 바꿔야 하면 param 명시해줌.
		//logger.info("검색 아이디 : " + id);
		String json = memberService.idCheck(id); // service에서 제이슨을 만듬. 화면이 넘어가지는 않음.
		return json; // 보낸것은 오브젝트
	}
	
	@RequestMapping(value = "/login.kitri", method = RequestMethod.GET)
	public String login() {   //단순 페이지 이동은 String
		return "user/login/login";   // view의 이름만 적으면 된다네
	}
	
	
	
//  @RequestMapping(value = "/login.kitri", method = RequestMethod.POST)
//  public String login(@RequestParam("id") String id, 
//                 @RequestParam("pass") String pass,
//                 HttpSession session) {   //인터셉터?가 돌아다니는 세션을 가져온다..?
//     MemberDto memberDto = memberService.loginMember(id, pass);
//     if(memberDto != null) {
//        session.setAttribute("userInfo", memberDto); // 정상적으로 가져오지 못하면 null뜬다
//        return "user/login/loginok";
//     } else {
//        return "user/login/loginfail";
//     }
//  }
	
	
	   
	@RequestMapping(value = "/login.kitri", method = RequestMethod.POST)
	public String login(@RequestParam Map<String, String> map, Model model) {   
						//인터셉터?가 돌아다니는 세션을 가져온다..? HttpSession session 대신에 Model model을 씀.
						// 넘어오는 값을 받는게 아니라 넘겨줄 값으로 바뀜(모델로 바뀜) 자기의 이름으로 집어넣어라 이름 없어도 됨.
		
		MemberDto memberDto = memberService.loginMember(map);
		if(memberDto != null) {
			model.addAttribute("userInfo", memberDto); // 정상적으로 가져오지 못하면 null뜬다
			return "user/login/loginok";
		} else {
			return "user/login/loginfail"; // 아무것도 안하면 포워드
		}
	}
	
	
//	model로 세션을 사용했으니 다른 방식으로 지움
//	@RequestMapping("/logout.kitri")
//	public String logout(HttpSession session) {
//		
//		session.removeAttribute("userInfo");
//		
//		return "redirect:/index.jsp"; // redirect할때 prefix와 suffix가 안붙음
//	}

	
//	setComplete는 세션을 모두 지우겠다. 스프링 세션이랑 서블릿 세션은 다름.
//	public String logout(@ModelAttribute MemberDto memberDto,SessionStatus sessionStatus) { // 스프링이 객체를 만들어버림.
	@RequestMapping("/logout.kitri")
	public String logout(@ModelAttribute("userInfo") MemberDto memberDto,SessionStatus sessionStatus) { // 스프링이 객체를 만들어버림.
			// @ModelAttribute 모델안에 있는 것을 가져와라. exception이 나오는 이유는 memberdto를 못받아 오기 때문 servlet-context에서 에러 잡고 날림.
		
//		MemberDto memberdto = session.getmemberdto; 얻어와서 널값이면 지우
		
		// @ModelAttribute 를 안썻을때는 모두 login으로 가짐. 새 객체를 만들기 때문.
		// 깡통 dto를 만들어 버리므로 memberDto만 하면 null이 아니므로 .getId()를 해줌.
//		if (memberDto.getId() != null) {
//			sessionStatus.setComplete();
//			return "redirect:/index.jsp"; // redirect할때 prefix와 suffix가 안붙음
//		} else {
//			return "redirect:/user/login.kitri"; // 로그인도 한적 없는데 로그인을 해라 - 다른사람이 로그아웃 주소를 알아냈을때. 치고 들어오면 널포인트남.
//		}
		
		sessionStatus.setComplete();
		return "redirect:/index.jsp"; // redirect할때 prefix와 suffix가 안붙음 
		
		
	}
	

	
	
	
	
}





