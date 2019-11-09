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
public class RetrieveTest {
	@Autowired
	private UserMapper userMapper;
	//查询单个实例，根据id
	@Test
	public void selectById() {
		User user =userMapper.selectById(1088250446457389058L);
		System.out.println(user);
	}
	
	//查询多个实例，根据id
	@Test
	public void selectByListId() {
		List<Long> idList=Arrays.asList(1088250446457389058L,1094592041087729666L,1088248166370832385L);
		List<User> userList=userMapper.selectBatchIds(idList);
		System.out.println(userList.size());
		
	}	
	//根据Map查询
	@Test
	public void selectMap() {
		//map.put();
		//map.put()
		//where name="" and  age=?
		//条件的关联关系是：and
		Map<String,Object> columnMap=new HashMap<>();
		columnMap.put("name", "王天风");//name 和 age指的是数据库中的字段的名称
		columnMap.put("age", 28);
		List<User> userList=userMapper.selectByMap(columnMap); 
		System.out.println("map查询方式"+userList.size());
		
		
	}
	//采用构造器查询
	//name包含雨，年龄小于40
	@Test
	public void selectByWrapper() {
		QueryWrapper<User> queryWrapper=new QueryWrapper<User>();
		queryWrapper.like("name","雨").lt("age", 40);
		List<User> userList=userMapper.selectList(queryWrapper);
		System.out.println("构造器输出:"+userList.size());
	}
	//名字中包含雨年并且龄大于等于20且小于等于40并且email不为空
	@Test
	public void selectByWrapper2() {
		QueryWrapper<User> queryWrapper=new QueryWrapper<User>();
		queryWrapper.like("name","雨").between("age", 20, 40).isNotNull("email");
		
		List<User> userList=userMapper.selectList(queryWrapper);
		System.out.println("构造器输出2:"+userList.size());
	}
	//名字为王姓或者年龄大于等于25，按照年龄降序排列，年龄相同按照id升序排列
	@Test
	public void selectByWrapper3() {
		QueryWrapper<User> queryWrapper=new QueryWrapper<User>();
		queryWrapper.likeRight("name","王").or().ge("age",25).orderByDesc("age").orderByAsc("id");
	
		//如果查询条件中有or,那么用Or（）连接前后条件
		List<User> userList=userMapper.selectList(queryWrapper);
		System.out.println("构造器输出3:"+userList.size());
	}
	//创建日期为2019年2月14日并且直属上级为名字为王姓
    // date_format(create_time,'%Y-%m-%d')='2019-02-14'and manager_id in (select id from user where name like '王%')

	@Test
	public void selectByWrapper4() {
		QueryWrapper<User> queryWrapper=new QueryWrapper<User>();
		//queryWrapper.likeRight("name","wang").or().ge("age",25).orderByDesc("age").orderByAsc("id");
		//queryWrapper.apply("date_format(create_time,'%Y-%m-%d')={0}","2019-02-14")
		queryWrapper.apply("date_format(create_time,'%Y-%m-%d')={0}","2019-02-14").inSql("manager_id","select id from user where name like'王%'");
		List<User> userList=userMapper.selectList(queryWrapper);
		System.out.println("构造器输出4:"+userList.size());
	}
	
	//名字为王姓并且（年龄小于40或邮箱不为空）
    //name like '王%' and (age<40 or email is not null)
	//lambda表达式
	@Test
	public void selectByWrapper5() {
		QueryWrapper<User> queryWrapper=new QueryWrapper<User>();
		//queryWrapper.likeRight("name","wang").or().ge("age",25).orderByDesc("age").orderByAsc("id");
		//queryWrapper.apply("date_format(create_time,'%Y-%m-%d')={0}","2019-02-14")
		queryWrapper.likeRight("name","王").and(wq->wq.lt("age",40).or().isNotNull("email"));
		List<User> userList=userMapper.selectList(queryWrapper);
		System.out.println("构造器输出5:"+userList.size());
	}
	//名字为王姓或者（年龄小于40并且年龄大于20并且邮箱不为空）
    //name like '王%' or (age<40 and age>20 and email is not null)

	@Test
	public void selectByWrapper6() {
		QueryWrapper<User> queryWrapper=new QueryWrapper<User>();
		//queryWrapper.likeRight("name","wang").or().ge("age",25).orderByDesc("age").orderByAsc("id");
		//queryWrapper.apply("date_format(create_time,'%Y-%m-%d')={0}","2019-02-14")
		queryWrapper.likeRight("name","王").or(wq->wq.lt("age", 40).gt("age",20).isNotNull("email"));
		List<User> userList=userMapper.selectList(queryWrapper);
		System.out.println("构造器输出6:"+userList.size());
	}
	
	//（年龄小于40或邮箱不为空）并且名字为王姓
    //(age<40 or email is not null) and name like '王%'
	@Test
	public void selectByWrapper7() {
		QueryWrapper<User> queryWrapper=new QueryWrapper<User>();
		//queryWrapper.likeRight("name","wang").or().ge("age",25).orderByDesc("age").orderByAsc("id");
		//queryWrapper.apply("date_format(create_time,'%Y-%m-%d')={0}","2019-02-14")
		queryWrapper.nested(wq->wq.lt("age", 40).or().isNotNull("email")).likeRight("name", "王");
		List<User> userList=userMapper.selectList(queryWrapper);
		System.out.println("构造器输出7:"+userList.size());
	}
	//备注：括号在前和括号在后不一样nested（）和or（）and()
	
	//年龄为30、31、34、35
    //age in(30、31、34、35)  

	
	@Test
	public void selectByWrapper8() {
		QueryWrapper<User> queryWrapper=new QueryWrapper<User>();
		//queryWrapper.likeRight("name","wang").or().ge("age",25).orderByDesc("age").orderByAsc("id");
		//queryWrapper.apply("date_format(create_time,'%Y-%m-%d')={0}","2019-02-14")
		queryWrapper.in("age", Arrays.asList(30,31,34,35));
		List<User> userList=userMapper.selectList(queryWrapper);
		System.out.println("构造器输出8:"+userList.size());
	}
	
	//9、只返回满足条件的其中一条语句即可
	//limit 1

	@Test
	public void selectByWrapper9() {
		QueryWrapper<User> queryWrapper=new QueryWrapper<User>();
		//queryWrapper.likeRight("name","wang").or().ge("age",25).orderByDesc("age").orderByAsc("id");
		//queryWrapper.apply("date_format(create_time,'%Y-%m-%d')={0}","2019-02-14")
		queryWrapper.in("age", Arrays.asList(30,31,34,35)).last("limit 1");
		List<User> userList=userMapper.selectList(queryWrapper);
		System.out.println("构造器输出9:"+userList.size());
	}
	//采用构造器查询
		//name包含雨，年龄小于40并且只查询id和name
		@Test
		public void selectByWrapper10() {
			QueryWrapper<User> queryWrapper=new QueryWrapper<User>();
			queryWrapper.select("id","name").like("name","雨").lt("age", 40);
			List<User> userList=userMapper.selectList(queryWrapper);
			System.out.println("构造器输出10:"+userList.size());
		}
		//采用构造器查询
		//name包含雨，年龄小于40并且排除两个字段
		@Test
		public void selectByWrapper11() {
			QueryWrapper<User> queryWrapper=new QueryWrapper<User>();
			queryWrapper.like("name","雨").lt("age", 40)
			.select(User.class,info->!info.getColumn().equals("create_time")&&!info.getColumn().equals("manager_id"));
			List<User> userList=userMapper.selectList(queryWrapper);
			System.out.println("构造器输出11:"+userList.size());
		}
		
		
		//condition条件
		//模拟前台传递参数，后台按参数进行查询
		@Test
		public void testCondition() {
			String name="王";
			String email="";
			condition(name,email);
			
		}
		public void condition(String name,String email) {
			QueryWrapper<User> queryWrapper=new QueryWrapper<User>();
			/*if(StringUtils.isNotEmpty("name")) {
				queryWrapper.like("name", name);
			}
			if(StringUtils.isNotEmpty("email")) {
				queryWrapper.like("email", email);
			}*/
			queryWrapper.like(StringUtils.isNotEmpty("name"), "name", name)
			.like(StringUtils.isNotEmpty("email"),"email", email);
			List<User> userList=userMapper.selectList(queryWrapper);
			System.out.println("########################构造器输出12:"+userList.size());
		}
		//传入实体对象
		@Test
		public void selectByWrapperEntity() {
			User whereUser=new User();
			whereUser.setName("刘红雨");
			whereUser.setAge(32);
			QueryWrapper<User> queryWrapper=new QueryWrapper<User>(whereUser);
			
			//下面的条件和传入的实体whereUser的查询互不干扰，都会出现在查询条件中
			//SELECT id,name,age,email,manager_id,create_time FROM user WHERE name=? AND age=? AND name LIKE ? AND age < ? 
			queryWrapper.like("name","雨").lt("age", 40);
			List<User> userList=userMapper.selectList(queryWrapper);
			System.out.println("####################构造器输出13:"+userList.size());
		}
		@Test
		public void selectByWrapperEntity2() {
			User whereUser=new User();
			whereUser.setName("刘红雨");
			whereUser.setAge(32);
			//前台传到后台的名字都要模糊查询，
			//1.在对象的字段中设置@TableField(condition=Sqlcondition.like)
			QueryWrapper<User> queryWrapper=new QueryWrapper<User>(whereUser);
			//输出语句：SELECT id,name,age,email,manager_id,create_time FROM user WHERE name LIKE CONCAT('%',?,'%') AND age=? 
			
			List<User> userList=userMapper.selectList(queryWrapper);
			System.out.println("####################构造器输出14:"+userList.size());
		}
		
		
		//AllEq用法
		@Test
		public void selectByWrapperAllEq() {
			
			QueryWrapper<User> queryWrapper=new QueryWrapper<User>();
			Map<String,Object> params=new HashMap<String,Object>();
			params.put("name", "王天风");
			params.put("age", 25);
			// queryWrapper.allEq(params);
			queryWrapper.allEq(params,false);//忽略掉为空的字段
			List<User> userList=userMapper.selectList(queryWrapper);
			//输出语句：SELECT id,name,age,email,manager_id,create_time FROM user WHERE name = ? AND age = ? 
			//备注：如果age为null，那么查询出来0条数据。SELECT id,name,age,email,manager_id,create_time FROM user WHERE name = ? AND age IS NULL 
			System.out.println("####################构造器输出15:"+userList.size());
		}
		//select<Msp<String,Object>>
		//针对报表，重组数据
		/*按照直属上级分组，查询每组的平均年龄、最大年龄、最小年龄。
		并且只取年龄总和小于500的组。
		select avg(age) avg_age,min(age) min_age,max(age) max_age
		from user
		group by manager_id
		having sum(age) <500
*/
		@Test
		public void selectByWrapperMaps() {
			
			QueryWrapper<User> queryWrapper=new QueryWrapper<User>();
			queryWrapper.select("avg(age) avg_age","min(age) min_age","max(age) max_age")
						.groupBy("manager_id").having("sum(age)<{0}",500);
			
			List<Map<String,Object>> userList=userMapper.selectMaps(queryWrapper);
			
			System.out.println("####################构造器输出16:"+userList.size());
		}
		
		
		
		//selectobj只返回一列到对象中
		//selectCount总的记录数
		//selectOne只返回一条记录,返回对象
		@Test
		public void selectByWrapperSelectOne() {
			QueryWrapper<User> queryWrapper=new QueryWrapper<User>();
			queryWrapper.like("name","刘红雨").lt("age", 40);
			User user=userMapper.selectOne(queryWrapper);
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!构造器输出17:"+user);
		}
		
		
		//MP还提供了Lambda构造器
		
		@Test
		public void  selectLambda() {
			//创建构造器,3中方式创建
			//LambdaQueryWrapper<User> lambda=new QueryWrapper<User>().lambda();
			//LambdaQueryWrapper<User> lambdaQueryWrapper=new LambdaQueryWrapper<User>();
			LambdaQueryWrapper<User> lambdaQuery=Wrappers.<User>lambdaQuery();
			
			lambdaQuery.like(User::getName,"雨").lt(User::getAge,40);
			List<User> userList=userMapper.selectList(lambdaQuery);
			
			
		}
		
		//物理分页查询测试
				@Test
				public void selectPage() {
					QueryWrapper<User> queryWrapper=new QueryWrapper<User>();
					queryWrapper.ge("age",26);
				    Page<User> page=new Page<User>(1,2);
				    
				    
				    IPage<User> iPage=userMapper.selectPage(page, queryWrapper);
				    
				    
				    System.out.println("总页数"+iPage.getPages());
				    System.out.println("总页数"+iPage.getTotal());
				    
				    List<User> userList=iPage.getRecords();
				    
					
				}
		
		
		
		
		
		
		
		
		
		
		
		
}
