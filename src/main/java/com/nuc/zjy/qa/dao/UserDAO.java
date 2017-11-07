package com.nuc.zjy.qa.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.nuc.zjy.qa.bean.User;

/**
 * @项目名称：QA
 * @类名称：UserDAO @类描述：
 *
 * @author 赵建银
 * @date 2017年11月7日
 * @time 上午8:15:53
 * @version 1.0
 */
@Mapper
public interface UserDAO {
	String TABLE_NAME = "user";
	String INSERT_FIELDS = "name,password,salt,head_url";
	String SELECT_FIELDS = "id," + INSERT_FIELDS;

	@Insert({ "insert into", TABLE_NAME, " (", INSERT_FIELDS, ") values (#{name},#{password},#{salt},#{headUrl})" })
	int addUser(User user);

	@Select({ "select", SELECT_FIELDS, "from", TABLE_NAME, "where id = #{id}" })
	User selectByid(int id);

	@Select({ "select", SELECT_FIELDS, "from", TABLE_NAME, "where name = #{name}" })
	User selectByName(String name);
	
	@Update({ "update", TABLE_NAME, "set password = #{password} where id = #{id}" })
	void updatePasword(User user);

	@Delete({ "delete from ", TABLE_NAME, " where id = #{id}" })
	void deleteById(int id);
}
