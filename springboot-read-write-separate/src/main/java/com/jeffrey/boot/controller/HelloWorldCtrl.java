package com.jeffrey.boot.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jeffrey.boot.service.DicService;

@RestController
public class HelloWorldCtrl {

	private final Logger log = LoggerFactory.getLogger(HelloWorldCtrl.class);
	
	@Autowired
	private DicService dicService;
	
	@GetMapping("/get")
	public Map<String, Object> helloworld() {
		Map<String, Object> map = new HashMap<String, Object>();
		//map.put("data", ((DicDao) SpringContextHolder.getBean("dicDao")).getAll());
		map.put("data", dicService.getAllDic());
		log.info("############################################");
		return map;
	}
}
