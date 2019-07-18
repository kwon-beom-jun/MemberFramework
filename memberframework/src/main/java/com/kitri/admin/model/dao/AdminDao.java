package com.kitri.admin.model.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.kitri.member.model.MemberDetailDto;

@Repository
public interface AdminDao {
	
	
	public List<MemberDetailDto> getmemberList(Map<String, String> map);

}
