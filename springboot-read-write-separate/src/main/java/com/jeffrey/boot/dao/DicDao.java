package com.jeffrey.boot.dao;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.jeffrey.boot.entity.RcDic;

public interface DicDao {
	
	@Select("SELECT * FROM rc_dic")
	@Results({
		@Result(property = "dicName",  column = "dic_name"),
		@Result(property = "dicCode", column = "dic_code"),
		@Result(property = "createTime", column = "create_time")
	})
	List<RcDic> getAll();
	
	/*@Select("SELECT * FROM users WHERE id = #{id}")
	@Results({
		@Result(property = "userSex",  column = "user_sex", javaType = UserSexEnum.class),
		@Result(property = "nickName", column = "nick_name")
	})
	UserEntity getOne(Long id);

	@Insert("INSERT INTO users(userName,passWord,user_sex) VALUES(#{userName}, #{passWord}, #{userSex})")
	void insert(UserEntity user);

	@Update("UPDATE users SET userName=#{userName},nick_name=#{nickName} WHERE id =#{id}")
	void update(UserEntity user);

	@Delete("DELETE FROM users WHERE id =#{id}")
	void delete(Long id);*/

}