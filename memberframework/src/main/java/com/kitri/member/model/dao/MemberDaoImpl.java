package com.kitri.member.model.dao;

import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kitri.member.model.*;
import com.kitri.sqlmap.MyBatisConfiguration;


@Repository
public class MemberDaoImpl implements MemberDao{
	
	private final String NAME_SPACE = "com.kitri.member.model.dao.memberDao";

	@Override
	public int idCheck(String id) {
		// 하나만 사용해야 되는 이유는 닫아야 되기 때문.
		SqlSession session = MyBatisConfiguration.getSqlSessionFactory().openSession();
		try {
			return session.selectOne(NAME_SPACE +".idCheck", id); // mapper의 id를 설정해주기. mapper의 namespace설정한것 쓰기. 다른 mapper의 이름이 겹칠 수 있기 때문./ 안써도됨.
		} finally {
			session.close();
		}
	}

	@Override
	public List<ZipcodeDto> zipSearch(String doro) {
		SqlSession session = MyBatisConfiguration.getSqlSessionFactory().openSession();
		try {
			return session.selectList(NAME_SPACE + ".zipSearch", doro); // mapper의 id를 설정해주기. mapper의 namespace설정한것 쓰기. 다른 mapper의 이름이 겹칠 수 있기 때문./ 안써도됨.
		} finally {
			session.close();
		}
	}

	@Override
	public int registerMember(MemberDetailDto memberDetailDto) {
		SqlSession session = MyBatisConfiguration.getSqlSessionFactory().openSession();
		try {
			session.insert(NAME_SPACE + ".registerMember", memberDetailDto); // mapper의 id를 설정해주기. mapper의 namespace설정한것 쓰기. 다른 mapper의 이름이 겹칠 수 있기 때문./ 안써도됨.
			session.commit();
			return 1;
		} finally {
			session.close();
		}
	}

	@Override
	public MemberDto loginMember(Map<String, String> map) {
		SqlSession session = MyBatisConfiguration.getSqlSessionFactory().openSession();
		try {
			return session.selectOne(NAME_SPACE +".loginMember", map); // mapper의 id를 설정해주기. mapper의 namespace설정한것 쓰기. 다른 mapper의 이름이 겹칠 수 있기 때문./ 안써도됨.
		} finally {
			session.close();
		}
	}

	@Override
	public MemberDetailDto getMember(String id) {
		SqlSession session = MyBatisConfiguration.getSqlSessionFactory().openSession();
		return null;
	}

	@Override
	public int modifyMember(String name) {
		SqlSession session = MyBatisConfiguration.getSqlSessionFactory().openSession();
		try {
			return session.selectOne(NAME_SPACE +".modifyMember", name); // mapper의 id를 설정해주기. mapper의 namespace설정한것 쓰기. 다른 mapper의 이름이 겹칠 수 있기 때문./ 안써도됨.
		} finally {
			session.close();
		}
	}

	@Override
	public int deleteMember(String id) {
		SqlSession session = MyBatisConfiguration.getSqlSessionFactory().openSession();
		return 0;
	}

	
	
	
}
