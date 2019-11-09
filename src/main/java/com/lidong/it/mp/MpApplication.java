/**   
* @Title: MpApplication.java 
* @Package com.lidong.it.mp 
* @Description: TODO 
* @author 杨小琪  
* @date 2019年11月8日 上午10:00:19 
* @version V1.0   
*/
package com.lidong.it.mp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/** 
* @ClassName: MpApplication 
* @Description: TODO 
* @author 杨小琪 
* @date 2019年11月8日 上午10:00:19  
*/
@SpringBootApplication
@MapperScan(value="com.lidong.it.mp.dao")
public class MpApplication {
	public static void main(String[] args) {
		SpringApplication.run(MpApplication.class, args);
	}
}
