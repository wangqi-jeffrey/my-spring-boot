package com.jeffrey.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeffrey.boot.dao.DicDao;
import com.jeffrey.boot.entity.RcDic;

@Service
public class DicService {

	@Autowired
	private DicDao dicDao;
	
	public List<RcDic> getAllDic() {
		return dicDao.getAll();
	}
}
