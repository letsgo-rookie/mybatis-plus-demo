package com.example.demo.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;

import lombok.Data;

@Data
public class User {
	
//	@TableId(type = IdType.AUTO)
	private Long id;
	
	private String name;
	private Integer age;
	private String email;
	
//	@TableField(fill = FieldFill.INSERT)
//	private Date createTime;
//	
//	//@TableField(fill = FieldFill.UPDATE)
//	@TableField(fill = FieldFill.INSERT_UPDATE)
//	private Date updateTime;
	
	@Version
	@TableField(fill = FieldFill.INSERT)
	private Integer version;
	
	@TableLogic
	@TableField(fill = FieldFill.INSERT)
	private Integer deleted;
}
