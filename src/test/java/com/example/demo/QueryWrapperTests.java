package com.example.demo;


import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QueryWrapperTests {
	
	@Autowired
	private UserMapper userMapper;
	
	@Test
	public void testDelete() {
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.isNull("name")
		.ge("age", 12)
		.isNotNull("email");
		int result = userMapper.delete(queryWrapper);
		System.out.println("delete return count = " + result);
	}
	
	@Test
	public void testSelectOne() {
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("name", "Billie");
		User user = userMapper.selectOne(queryWrapper);
		System.out.println(user);
	}
	
	@Test
	public void testSelectCount() {
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.between("age", 20, 30);
		Integer count = userMapper.selectCount(queryWrapper);
		System.out.println(count);
	}
	
	@Test
	public void testSelectObjs() {
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		//queryWrapper.in("id", 1, 2, 3);
		queryWrapper.inSql("id", "select id from user where delete = 1");
		List<Object> objects = userMapper.selectObjs(queryWrapper);
		//返回值是Object列表
		objects.forEach(System.out::println);
	}
	

}
