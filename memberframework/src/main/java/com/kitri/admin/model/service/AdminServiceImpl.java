package com.kitri.admin.model.service;

import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kitri.admin.model.dao.AdminDao;
import com.kitri.member.model.MemberDetailDto;


@Service
public class AdminServiceImpl implements AdminService {

	private static AdminService adminService;
	
	@Autowired
	private AdminDao adminDao;
	
	
	@Override
	public String getmemberList(Map<String, String> map) {
		
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("key", key);
//		map.put("word",word);
		
		List<MemberDetailDto> list = adminDao.getmemberList(map);
		JSONObject json = new JSONObject();
		JSONArray jarray = new JSONArray(list);
		json.put("memberlist",jarray);
		
		return json.toString();
	}

	
}











