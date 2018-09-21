package com.spring.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.spring.domain.Criteria;
import com.spring.domain.ReplyVO;
import com.spring.persistence.ReplyDAO;

@Service
public class ReplyServiceImpl implements ReplyService{

	@Inject
	private ReplyDAO replydao;
	
	@Override
	public void addReply(ReplyVO vo) throws Exception {
		replydao.create(vo);
		
	}

	@Override
	public List<ReplyVO> listReply(Integer bno) throws Exception {
	
		return replydao.list(bno);
	}

	@Override
	public void modifyReply(ReplyVO vo) throws Exception {
		replydao.update(vo);
		
	}

	@Override
	public void removeReply(Integer rno) throws Exception {
		replydao.delete(rno);
		
	}

	@Override
	public List<ReplyVO> listReplyPage(Integer bno, Criteria criteria) throws Exception {
		
		return replydao.listPage(bno, criteria);
	}
	@Override
	public int count(Integer bno) throws Exception{
		return replydao.count(bno);
	}

}
