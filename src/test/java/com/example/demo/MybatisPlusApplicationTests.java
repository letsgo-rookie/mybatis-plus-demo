package com.example.demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
	
	@Test
	public void testInsert(){
		User user = new User();
		user.setName("Helen");
		user.setAge(18);
		user.setEmail("55317332@qq.com");
		int result = userMapper.insert(user);
		System.out.println(result); //影响的行数18        
		System.out.println(user); //id自动回填19    
	}
	@Test
	public void testUpdateById(){
		User user = new User();
		user.setId(1L);
		user.setAge(28);
		int result = userMapper.updateById(user);
		System.out.println(result);
		}
	
	@Test
	public void testOptimisticLocker(){
		//查询    
		User user = userMapper.selectById(1);
		//修改数据    
		user.setName("Helen Yao");
		user.setEmail("helen@qq.com");
		//执行更新
		userMapper.updateById(user);
		}
	
	@Test
	public void testOptimisticLockerFail() {
		//查询    
		User user = userMapper.selectById(1L);
		//修改数据    
		user.setName("Helen Yao1");    
		user.setEmail("helen@qq.com1");
		//模拟取出数据后，数据库中version实际数据比取出的值大，即已被其它线程修改并更新了version    
		user.setVersion(user.getVersion() - 1);    
		//执行更新17    
		userMapper.updateById(user);
	}
	
	@Test
	public void testSelectByMap(){
		HashMap<String, Object> map = new HashMap<>();
		map.put("name", "Helen");
		map.put("age", 18);
		List<User> users = userMapper.selectByMap(map);
		users.forEach(System.out::println);
		}
	
	@Test
	public void testSelectPage(){
		Page<User> page = new Page<>(1,3);
		userMapper.selectPage(page, null);
		page.getRecords().forEach(System.out::println);
		System.out.println(page.getCurrent());
		System.out.println(page.getPages());
		System.out.println(page.getSize());
		System.out.println(page.getTotal());
		System.out.println(page.hasNext());
		System.out.println(page.hasPrevious());
	}
	
	@Test
	public void testSelectMapsPage(){
		Page<User> page = new Page<>(1, 5);
		IPage<Map<String, Object>> mapIPage = userMapper.selectMapsPage(page, null);
		//注意：此行必须使用 mapIPage 获取记录列表，否则会有数据类型转换错误
		mapIPage.getRecords().forEach(System.out::println);
		System.out.println(page.getCurrent());
		System.out.println(page.getPages());
		System.out.println(page.getSize());
		System.out.println(page.getTotal());
		System.out.println(page.hasNext());
		System.out.println(page.hasPrevious());
	}
	
	@Test
	public void testLogicDelete(){
		int result = userMapper.deleteById(1L);
		System.out.println(result);
	}
	
	
	
	@Test
	public void testLogicDeleteSelect(){
		User user = new User();
		List<User> users = userMapper.selectList(null);
		users.forEach(System.out::println);
	}
	
	@Test
	public void testPerformance() {
		User user = new User();
		user.setName("我是Helen");
		user.setEmail("helen@sina.com");
		user.setAge(18);
		userMapper.insert(user);
	}
	
	
}
