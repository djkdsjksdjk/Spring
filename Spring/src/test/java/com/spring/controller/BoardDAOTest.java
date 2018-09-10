package com.spring.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.domain.BoardVO;
import com.spring.persistence.BoardDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class BoardDAOTest {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardDAOTest.class);//로깅
	
	@Inject
	private BoardDAO dao;
	
	
	//@Test
	public void testUpdate() throws Exception{
		BoardVO boardVO = new BoardVO();
		boardVO.setBno(1);
		boardVO.setTitle("수정된 제목입니다.");
		boardVO.setContent("수정된 내용입니다.");
		dao.update(boardVO);
	}
	//@Test
	public void testRead() throws Exception{
		logger.info(dao.read(1).toString());
	}
	
	
	
	//글추가
	//@Test
	public void testCreate() throws Exception{
		
		BoardVO boardVO = new BoardVO();
		boardVO.setTitle("네번째 제목입니다.");
		boardVO.setContent("네번째 내용입니다.");
		boardVO.setWriter("테스트04 four");
		dao.create(boardVO);
		
	}
	
	@Test
	public void testDelete() throws Exception{
		dao.delete(21);
	}
	
	@Test
	public void testlistAll() throws Exception{
		dao.listAll();
		
	}
}
