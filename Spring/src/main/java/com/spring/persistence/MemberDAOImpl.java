package com.spring.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.spring.domain.MemberVO;

@Repository
public class MemberDAOImpl implements MemberDAO {
private static final Logger logger = LoggerFactory.getLogger(MemberDAOImpl.class);

	@Inject
	private SqlSession SqlSession;
	private static final String namespace = "com.spring.mapper.MemberMapper";

	
	@Override
	public String getTime() {
		//return SqlSession.selectOne("com.spring.mapper.MemberMapper.getTime");
		logger.info("getTime()~~~~~~~~~~~~~~~~~~~~");
		
		return SqlSession.selectOne(namespace + ".getTime");
	}

	@Override
	public void insertMember(MemberVO vo) {
		
		logger.info("insertMember(MemberVO vo)~~~~~~~~~~~~");
		logger.info(vo.getUserid() + " - " + vo.getUsername());
		SqlSession.insert(namespace + ".insertMember", vo);

	}

}
