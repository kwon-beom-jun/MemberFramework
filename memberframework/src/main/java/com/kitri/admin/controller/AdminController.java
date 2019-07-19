package com.kitri.admin.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kitri.admin.model.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired // servlet-context에서 controller에 dao,service를 주입하는게 없는데 찾을 수 있게 하는 방법
	private AdminService adminService;
	
	@RequestMapping(value = "/mvmemberlist.kitri", method = RequestMethod.GET)
	public String mvmemberlist(){
		return "admin/member/memberlist";
	}
	
//		int cnt = memberService.registerMember(memberDetailDto);
//		if (cnt != 0) {
//			model.addAttribute("registerInfo",memberDetailDto); // model을 직접적으로 설정할때 사용
//			return "user/member/registerok"; // view
//		}
//		return "user/member/registerfail"; 
	@RequestMapping(value = "/getmemberlist.kitri")
	public @ResponseBody String getmemberlist(Map<String, String> map) { // "key" : key  / "word" : word
		String json = adminService.getmemberList(map);
		return json;
	}
	
//	-----------------------------------------------------------------------------------
	
//	수정페이지로 단순 이동
//	@RequestMapping("/modify.kitri")
//	public String modify() {
//		return "user/member/modified";
//	}
	
//	수정 작업
//	@RequestMapping("/modifyuser.kitri")
//	public String modifyuser(@RequestParam()) {
//		
//		return "";
//	}
	
	
	
}
















