/**   
* @Title: MpTest.java 
* @Package com.lidong.it.test 
* @Description: TODO 
* @author 杨小琪  
* @date 2019年11月8日 上午10:11:22 
* @version V1.0   
*/
package com.lidong.it.mp;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lidong.it.mp.dao.UserMapper;
import com.lidong.it.mp.entity.User;


/** 
* @ClassName: MpTest 
* @Description: TODO 
* @author 杨小琪 
* @date 2019年11月8日 上午10:11:22  
*/
@SpringBootTest
@RunWith(SpringRunner.class)
public class MpTest {
	@Autowired
	private UserMapper userMapper;
	
	@Test
	public void select () {
		User user=new User();
		user.setName("XXX");
		user.setAge(21);
		user.setManagerId(1088248166370832385L);
		user.setEmail("xxx@qq.com");
		user.setCreateTime(LocalDateTime.now()); 
		user.setRemark("11");
		user.setRemark2("22");
		int rows=userMapper.insert(user);
		System.out.println("影响的行数："+rows);
	}
	

}
