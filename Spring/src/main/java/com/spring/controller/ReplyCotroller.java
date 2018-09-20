package com.spring.controller;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.service.ReplyService;

public class ReplyCotroller {

	@RestController
	@RequestMapping("/replies")
	public class ReplyController{
		@Inject
		private ReplyService service;
		
	}
}
