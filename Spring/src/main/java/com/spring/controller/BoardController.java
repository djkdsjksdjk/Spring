package com.spring.controller;


import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.domain.BoardVO;
import com.spring.service.BoardService;

@Controller
@RequestMapping("/board/*")
public class BoardController {

	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);//로깅
	@Inject
	private BoardService service;
	
	@RequestMapping(value = "/listAll", method = RequestMethod.GET)
	public void listAll(Model model) throws Exception{
		logger.info("show listAll ~~~~~~~~~~~~");
		
		model.addAttribute("list", service.listAll());
		
		
		
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
public void registerGET(BoardVO boardVO, Model model) throws Exception{
		logger.info("regiter GET~~~~~~~");
		
	}
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerPOST(BoardVO boardVO, Model model) throws Exception{
		logger.info("regiter POST~~~~~~~");
		
		service.create(boardVO); //등록
		model.addAttribute("result", "success");
		
		return "/board/success"; //success.jsp 페이지 호출

}
}

