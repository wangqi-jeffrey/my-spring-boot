package com.neo.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.neo.util.AESUtil;

/**
 * 测试计划任务
 * @author Jeffrey
 * @since 2017年11月2日 上午10:39:43
 */
@Component
public class ScheduledTasks2 {
	private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks2.class);
	
	// 当前任务执行完成后过1分钟再执行
    @Scheduled(fixedDelay = 1000 * 30)
    public void loadSystemConfig() {
    	logger.info(">>>>>>>>>>>>>>>aaaaaaa计划任务执行开始<<<<<<<<<<<<<<<<<");
    	logger.info("aaaaaaa计划任务执行了。。。。。。。。。。。。。。。。");
    	try {
	    	for (long i = 0; i < 100000 * 5; i++) {
	    		String content = "我爱你";
	
	    		String key;
				key = AESUtil.createKeyPairs();
				
	    		String encrypt = AESUtil.aesEncrypt(content, key);
	
	    		String decrypt = AESUtil.aesDecrypt(encrypt, key);
			}
    	} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	logger.info("aaaaaaa计划任务事物提交了。。。。。。。。。。。。。。。");
		logger.info(">>>>>>>>>>>>>>>aaaaaaa计划任务执行结束<<<<<<<<<<<<<<<<<");
    }
    
    @Scheduled(fixedDelay = 1000 * 40)
    public void loadSystemConfig2() {
    	logger.info(">>>>>>>>>>>>>>>bbbbbbb计划任务执行开始<<<<<<<<<<<<<<<<<");
    	logger.info("bbbbbbb计划任务执行了。。。。。。。。。。。。。。。。");
    	try {
    		for (long i = 0; i < 100000 * 5; i++) {
    			String content = "我爱你";
    			
    			String key;
    			key = AESUtil.createKeyPairs();
    			
    			String encrypt = AESUtil.aesEncrypt(content, key);
    			
    			String decrypt = AESUtil.aesDecrypt(encrypt, key);
    		}
    	} catch (Exception e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	logger.info("bbbbbbb计划任务事物提交了。。。。。。。。。。。。。。。");
    	logger.info(">>>>>>>>>>>>>>>bbbbbbb计划任务执行结束<<<<<<<<<<<<<<<<<");
    }
}
