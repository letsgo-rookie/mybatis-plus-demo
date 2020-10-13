package com.example.demo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;

@SpringBootTest
@RunWith(SpringRunner.class)
class MybatisPlusApplicationTests {
	
	@Autowired   
	private UserMapper userMapper;

	@Test
	void contextLoads() {
	}

	@Test
	public void test() {
		System.out.println(("----- selectAll method test ------"));
		//UserMapper 中的 selectList() 方法的参数为 MP 内置的条件封装器 Wrapper12       
		//所以不填写就是无任何条件13        
		List<User> users = userMapper.selectList(null);
		users.forEach(System.out::println);
	}
	
}
