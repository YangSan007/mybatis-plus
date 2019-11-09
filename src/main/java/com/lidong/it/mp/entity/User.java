/**   
* @Title: User.java 
* @Package com.lidong.it.mp.entity 
* @Description: TODO 
* @author 杨小琪  
* @date 2019年11月8日 上午10:02:26 
* @version V1.0   
*/
package com.lidong.it.mp.entity;

import java.time.LocalDateTime;

import org.omg.CORBA.TRANSIENT;

import com.baomidou.mybatisplus.annotation.SqlCondition;
import com.baomidou.mybatisplus.annotation.TableField;

import lombok.Data;



//@Data
public class User {
	//主键
	private Long id;
	//姓名
	@TableField(condition=SqlCondition.LIKE)
	private String name;
	//年龄
	private Integer age;
	//邮箱
	private String email;
	//直属上级
	private Long managerId;
	//创建时间--被忽略字段
	private LocalDateTime createTime;
	//被忽略字段
	@TableField(exist=false)
	private String remark;
	//不对应数据库表字段的属性
	private transient String remark2;
	

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getManagerId() {
		return managerId;
	}
	public void setManagerId(Long managerId) {
		this.managerId = managerId;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}
	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getRemark2() {
		return remark2;
	}
	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

}
