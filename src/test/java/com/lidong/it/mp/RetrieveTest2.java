/**   
* @Title: RetrieveTest.java 
* @Package com.lidong.it.mp 
* @Description: TODO 
* @author 杨小琪  
* @date 2019年11月8日 下午4:18:06 
* @version V1.0   
*/
package com.lidong.it.mp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lidong.it.mp.dao.UserMapper;
import com.lidong.it.mp.entity.User;

/** 
* @ClassName: RetrieveTest 
* @Description: TODO 
* @author 杨小琪 
* @date 2019年11月8日 下午4:18:06  
*/
@SpringBootTest
@RunWith(SpringRunner.class)
public class RetrieveTest2 {
	@Autowired
	private UserMapper userMapper;
	
		//物理分页查询测试
				@Test
				public void selectPage() {
					QueryWrapper<User> queryWrapper=new QueryWrapper<User>();
					queryWrapper.ge("age",26);
					
				   Page<User> page=new Page<>(1,2);
				    
				    
				    IPage<User> iPage=userMapper.selectPage(page, queryWrapper);
				    
				    
				    System.out.println("总页数"+iPage.getPages());
				    System.out.println("总页数"+iPage.getTotal());
				    
				    List<User> userList=iPage.getRecords();
				    
					
				}
		
				/*@Test
				public void selectPage2() {
					QueryWrapper<User> queryWrapper=new QueryWrapper<User>();
					queryWrapper.ge("age",26);
					
				    Page<User> page=new Page<>(1,1);
				    
				    
				   // IPage<User> iPage=userMapper.selectPage(page, queryWrapper);
				    IPage<Map<String,Object>> iPage=userMapper.selectMapsPage(page, queryWrapper);
				    
				    System.out.println("总页数"+iPage.getPages());
				    System.out.println("总页数"+iPage.getTotal());
				    
				    List<Map<String,Object>> userList=iPage.getRecords();
				    userList.forEach(System.out::println);
					
				}*/
		
		
		
		
		
		
		
		
		
		
}
