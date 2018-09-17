package com.spring.controller;


import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.domain.BoardVO;
import com.spring.domain.Criteria;
import com.spring.domain.PageMaker;
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
	public String registerPOST(BoardVO boardVO, RedirectAttributes redirectAtt) throws Exception{
		logger.info("regiter POST~~~~~~~");
		
		service.create(boardVO); //등록
		redirectAtt.addFlashAttribute("result", "success"); //${result}
		//model.addAttribute("result", "success");
		
		//return "/board/success"; //success.jsp 페이지 호출
		
		return "redirect:/board/listAll";

}
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public void read(@RequestParam("bno") int bno, Model model) throws Exception{
		model.addAttribute("boardVO", service.read(bno));
	}
	
	@RequestMapping(value = "/readPage", method = RequestMethod.GET)
	public void read(@RequestParam("bno")int bno, 
			@ModelAttribute("criteria") Criteria criteria,
			Model model)throws Exception{
		model.addAttribute(service.read(bno));
	}
	
	
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String remove(@RequestParam("bno") int bno, 
			Criteria criteria,
			       RedirectAttributes redirectAtt)throws Exception{
		service.delete(bno);
		redirectAtt.addFlashAttribute("page", criteria.getPage());
		redirectAtt.addFlashAttribute("perPageNum", criteria.getPerPageNum());
		redirectAtt.addFlashAttribute("msg", "success"); //${msg_remove}
		return "redirect:/board/listAll";
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modifyPOST(BoardVO boardVO,
			RedirectAttributes redirectAtt)throws Exception{
		logger.info("modify POST~~~~~~~");
		service.update(boardVO);
		redirectAtt.addFlashAttribute("result", "success"); //${msg_update}
		return "redirect:/board/listAll";
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public void modifyGET(int bno, Model model) throws Exception{
		model.addAttribute(service.read(bno));
	}
	
	@RequestMapping(value = "/listCri", method = RequestMethod.GET)
	public void ListCri(Criteria criteria, Model model) throws Exception{
		logger.info("listCri~~~~~~~");
		
		model.addAttribute("list", service.listCriteria(criteria));
	}
	@RequestMapping(value = "/modifyPage", method = RequestMethod.GET)
	public void modifyPageGET(@RequestParam("bno") int bno, 
			                  @ModelAttribute("criteria") Criteria criteria, 
			                  Model model) throws Exception {
		
		model.addAttribute(service.read(bno));
	}
	
	@RequestMapping(value = "/modifyPage", method = RequestMethod.POST)
	public String modifyPagePOST(BoardVO boardVO, Criteria criteria, 
			                     RedirectAttributes redirectAtt) throws Exception {
		service.update(boardVO);
		
		redirectAtt.addAttribute("page", criteria.getPage());
		redirectAtt.addAttribute("perPageNum", criteria.getPerPageNum());
		redirectAtt.addFlashAttribute("result", "success");
		
		
		/*var result = 'success';
		var result = ${result};  
		if (result == 'success') {
			alert("글이 수정되었습니다.");
			}*/
			
		
		return "redirect:/board/listPage";
		
	}
	
	
	@RequestMapping(value = "/listPage" , method = RequestMethod.GET)
	public void listPage(Criteria criteria, Model model)throws Exception{
		
		logger.info("criteria.perPageNum: " + criteria.getPerPageNum());
		logger.info(criteria.toString());
		
		model.addAttribute("list", service.listCriteria(criteria));
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(criteria);
		pageMaker.setTotalCount(service.listCountCriteria(criteria));
		
		//pageMaker.setTotalCount(131); //전체데이터 갯수
		
		model.addAttribute("pageMaker", pageMaker);
	}
	
	
	/////////////////////////////////
	//int bno = Integer.parseInt(request.getParameter("bno"));
}

