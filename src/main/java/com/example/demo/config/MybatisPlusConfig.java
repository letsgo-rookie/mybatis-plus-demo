package com.example.demo.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;

@EnableTransactionManagement
@Configuration
//@MapperScan("com.example.demo.mapper")
public class MybatisPlusConfig {
	
	@Bean
	public OptimisticLockerInterceptor optimisticLockerInterceptor(){
		return new OptimisticLockerInterceptor();
	}
	
	@Bean
	public PaginationInterceptor paginationInterceptor() {
		return new PaginationInterceptor();
	}
	
	@Bean
	public ISqlInjector sqlInjector() {
		return new LogicSqlInjector();
	}
	
	@Bean
	@Profile({"dev","test"})// 设置 dev test 环境开启
	public PerformanceInterceptor performanceInterceptor(){
		PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
		performanceInterceptor.setMaxTime(200);
		//ms，超过此处设置的ms则sql不执行
		performanceInterceptor.setFormat(true);
		return performanceInterceptor;
	}

}
