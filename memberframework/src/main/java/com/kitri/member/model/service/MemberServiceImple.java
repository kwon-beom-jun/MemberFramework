package com.kitri.member.model.service;

import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kitri.member.model.*;
import com.kitri.member.model.dao.MemberDao;

import jdk.nashorn.api.scripting.JSObject;




@Service
public class MemberServiceImple implements MemberService{
	
	/* 우리가 만들지 않음. servlet-context에서 객체생성 못함.
	 * //----------------------------------------------------- private static
	 * MemberService memberService;
	 * 
	 * static { memberService = new MemberServiceImple(); }
	 * 
	 * private MemberServiceImple() {} // 외부에서 객체 생성 못하게 함.
	 * 
	 * public static MemberService getMemberService() { return memberService; }
	 * 
	 * // 싱글톤 패턴
	 */
	
	@Autowired
	private MemberDao memberDao;
	
	
	
	@Override
	public String idCheck(String id) {
		
		int cnt = memberDao.idCheck(id);
//		System.out.println("cnt : " + cnt);
//		String result = "";
//		result += "<idcount>\n";
//		result += "<cnt>" + cnt + "</cnt>\n";
//		result += "</idcount>";
		
		// json으로 바꿈
		JSONObject json = new JSONObject();
		json.put("idcount", cnt);
		

		return json.toString(); // {"idcount" : 0}
	}

	
	
	
	@Override
	public String zipSearch(String doro) {
		
		List<ZipcodeDto> list = memberDao.zipSearch(doro);
		JSONObject json = new JSONObject();
		JSONArray jarray = new JSONArray(list); // 쿼리에서 합쳐서 가져와도 됨. 안합치면 html에서 합쳐야뎀. // dto의 프로퍼티를 이용함.
//		for (ZipcodeDto zipcodeDto : list) {
//			JSONObject zipcode = new JSONObject();
//			zipcode.put("zipcode", zipcodeDto.getZipcode());
//			zipcode.put("address", zipcodeDto.getSido() + " " 
//									+ zipcodeDto.getGugun() + " " 
//									+ zipcodeDto.getUpmyon() + " " 
//									+ zipcodeDto.getDoro() + " " 
//									+ zipcodeDto.getBuildingNumber() + " " 
//									+ zipcodeDto.getSigugunBuildingName());
//			jarray.put(zipcode);
//		}
		json.put("ziplist", jarray);
//		System.out.println(json.toString()); 너무 긴 배열이 콘솔창에 찍히면 이클립스에서 감당못해 뻑남.
		return json.toString();
	}
	
//	{"ziplist", [{"zipcode" : "12345","address" : "서울시..", ....}
//				{"zipcode" : "12345","address" : "서울시..", ....}
//				{"zipcode" : "12345","address" : "서울시..", ....}
//				{"zipcode" : "12345","address" : "서울시..", ....}
//				]
//	} 
	
	
	
	
	
	@Override
	public int registerMember(MemberDetailDto memberDetailDto) {
		return memberDao.registerMember(memberDetailDto);
	}

	@Override
	public MemberDto loginMember(Map<String, String> map) {
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("userid", id);
//		map.put("userpwd", pass);
		return memberDao.loginMember(map); // 프레임워크는 값을 하나만 넣을 수 있어서 map을 사용함.
	}

	@Override
	public MemberDetailDto getMember(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int modifyMember(String name) {
		return 0;
	}

	@Override
	public int deleteMember(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	
}
