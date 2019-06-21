package com.kitri.sqlmap;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisConfiguration {

	private static SqlSessionFactory sqlSessionFactory;
		
	static {
		try {
			String resource = "com/kitri/sqlmap/configuration.xml";
			// reader = 한글사용 쉬움 / xml파일들을 resource라고도 함. - arparch io 임포트
			Reader reader = Resources.getResourceAsReader(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}
	
	
	
}
