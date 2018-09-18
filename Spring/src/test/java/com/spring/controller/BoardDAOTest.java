package com.spring.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.spring.domain.BoardVO;
import com.spring.domain.Criteria;
import com.spring.domain.SearchCriteria;
import com.spring.persistence.BoardDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class BoardDAOTest {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardDAOTest.class);//로깅
	
	@Inject
	private BoardDAO dao;
	
	@Test
	public void testSearchParameter() throws Exception{
		SearchCriteria criteria = new SearchCriteria();
		criteria.setPage(1);
		criteria.setSearchType("w");
		criteria.setKeyword("test");
		
		logger.info("================================================");
		
		List<BoardVO> list = dao.listSearch(criteria);
		
		for(BoardVO boardVO : list){
			logger.info(boardVO.getBno() + ": " + boardVO.getTitle());
		}
		logger.info("================================================");
		
		logger.info("COUNT: " + dao.listSearchCount(criteria));
	}
	
	
	@Test
	public void testURI() throws Exception{
		//스프링에서 제공하는 추상클래스 (웹페이지에서 사용)
		UriComponents uriComponents = 
				UriComponentsBuilder.newInstance()
				    .path("/board/read").queryParam("bno", 12)
				    .queryParam("perPageNum", 20).build();
	
		logger.info(uriComponents.toString());
		//board/read?bno12&perPageNum20
	}
	@Test
	public void testURI2() throws Exception{
		//스프링에서 제공하는 추상클래스 (웹페이지에서 사용)
				UriComponents uriComponents = 
						UriComponentsBuilder.newInstance()
						    .path("/{module}/{page}").queryParam("bno", 12)
						    .queryParam("perPageNum", 20).build()
						    .expand("board", "read")
						    .encode();
			
				logger.info(uriComponents.toString());
				// /board/read?bno=12&perPageNum=20
	}
	
	
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
	
	//@Test
	public void testDelete() throws Exception{
		dao.delete(21);
	}
	
	
	public void testlistAll() throws Exception{
		dao.listAll();
		
	}
	//@Test
	public void testlistpage() throws Exception{
		int page = 0; //1페이지는 (0,10) 2페이지 (10,10) 3페이지(20,10)
		
		List<BoardVO> list = dao.listPage(page);
		for(BoardVO boardVO : list){
			logger.info(boardVO.getBno() + ":" + boardVO.getTitle());
		}
		
	}
	//@Test
	public void testListCriteria() throws Exception{
		
		Criteria criteria = new  Criteria();
		criteria.setPage(2);
		//2page limit 20,20
		//limit sql에서 불러올 건수
		criteria.setPerPageNum(20);
		
		List<BoardVO> list = dao.listCriteria(criteria);
		for(BoardVO boardVO : list){
			logger.info(boardVO.getBno() + ":" + boardVO.getTitle());
		}
	}
}
