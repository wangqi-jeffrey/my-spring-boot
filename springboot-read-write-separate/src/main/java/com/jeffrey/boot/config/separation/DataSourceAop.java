package com.jeffrey.boot.config.separation;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * @ClassName: DataSourceAop
 * @Description: 本地线程拦截处理
 * @author yanzy
 * @date 2017年1月18日 下午3:32:19
 */
@Profile({"local", "dev", "test", "prod"})
@Aspect
@Component
public class DataSourceAop {
	Logger log = Logger.getLogger(DataSourceAop.class);

	@Before("execution(* com.jeffrey.boot.service..*.select*(..)) || execution(* com.jeffrey.boot.service..*.get*(..)) || execution(* com.jeffrey.boot.service..*.find*(..))")
	public void setReadDataSourceType() {
		DataSourceContextHolder.read();
		log.info("================================================dataSource切换到：Read");
	}

	@Before("execution(* com.jeffrey.boot.service..*.insert*(..)) || execution(* com.jeffrey.boot.service..*.create*(..)) || execution(* com.jeffrey.boot.service..*.save*(..)) || execution(* com.jeffrey.boot.service..*.update*(..)) || execution(* com.jeffrey.boot.service..*.edit*(..)) || execution(* com.jeffrey.boot.service..*.del*(..)) || execution(* com.jeffrey.boot.service..*.delete*(..)) || execution(* com.jeffrey.boot.service..*.add*(..)) ")
	public void setWriteDataSourceType() {
		DataSourceContextHolder.write();
		log.info("================================================dataSource切换到：write");
	}
}
