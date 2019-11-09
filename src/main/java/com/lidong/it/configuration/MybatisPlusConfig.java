/**   
* @Title: MybatisPlusConfig.java 
* @Package com.lidong.it.configuration 
* @Description: TODO 
* @author 杨小琪  
* @date 2019年11月9日 上午10:59:19 
* @version V1.0   
*/
package com.lidong.it.configuration;

import java.util.Properties;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;

@Transactional
@Configuration
@MapperScan("com.lidong.it.configuration")
public class MybatisPlusConfig {
	@Bean
	 public PaginationInterceptor paginationInterceptor() {

		return new PaginationInterceptor();

		    }
	 @Bean

	    public PerformanceInterceptor performanceInterceptor() {

	PerformanceInterceptor performanceInterceptor =new PerformanceInterceptor();

	        //格式化sql语句

	        Properties properties =new Properties();

	        properties.setProperty("format", "faalse");

	        performanceInterceptor.setProperties(properties);

	        return performanceInterceptor;

	    }
}
