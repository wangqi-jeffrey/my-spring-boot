package com.jeffrey.boot.config.separation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.jeffrey.boot.spring.SpringContextHolder;

@Configuration
@AutoConfigureAfter({ DataSourceConfiguration.class })
public class MybatisConfiguration extends MybatisAutoConfiguration {
	Logger log = Logger.getLogger(MybatisConfiguration.class);
	
    @Value("${datasource.readSize}")
    private String dataSourceSize;

    @Bean
    public SqlSessionFactory sqlSessionFactorys() throws Exception {
        log.info("-------------------- 重载父类 sqlSessionFactory init ---------------------");
        return super.sqlSessionFactory(roundRobinDataSouceProxy());
    }

    @Resource(name = "writeDataSource")
	private DataSource writeDataSource;

	@Resource(name = "readDataSource1")
	private List<Object> readDataSource1;
    /**
     * 有多少个数据源就要配置多少个bean
     * @return
     */
    @Bean
    public AbstractRoutingDataSource roundRobinDataSouceProxy() {
        int size = Integer.parseInt(dataSourceSize);
        MyAbstractRoutingDataSource proxy = new MyAbstractRoutingDataSource(size);
        Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
        //DataSource writeDataSource = SpringContextHolder.getBean("writeDataSource");
        // 写
        //targetDataSources.put(DataSourceType.write.getType(), SpringContextHolder.getBean("writeDataSource"));
        targetDataSources.put(DataSourceType.write.getType(), writeDataSource);
        targetDataSources.put(DataSourceType.read.getType(), readDataSource1);
        /*for (int i = 0; i < size; i++) {
            targetDataSources.put(i, SpringContextHolder.getBean("readDataSource" + (i + 1)));
        }*/
        proxy.setDefaultTargetDataSource(writeDataSource);
        proxy.setTargetDataSources(targetDataSources);
        return proxy;
    }

}
