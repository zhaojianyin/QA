package com.nuc.zjy.qa.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import com.nuc.zjy.qa.bean.Message;

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
public interface MessageDAO {
	String TABLE_NAME = "message";
	String INSERT_FIELDS = "from_id,to_id,content,has_read,create_date,conversation_id";
	String SELECT_FIELDS = "id," + INSERT_FIELDS;

	@Insert({ "insert into", TABLE_NAME, " (", INSERT_FIELDS,
			") values (#{fromId},#{toId},#{content},#{hasRead},#{createDate},#{conversationId})" })
	int addMessage(Message message);

	@Select({ "select", SELECT_FIELDS, "from", TABLE_NAME, "where id = #{id}" })
	Message selectByid(int id);

	@Select({ "select", SELECT_FIELDS, "from", TABLE_NAME,
			"where conversation_id = #{conversationId} order by create_date" })
	List<Message> getConversionId(@Param("conversationId") String conversationId);

	// select *, count(*) as id from (select * from message where from_id = userid
	// or to_id = userid order by create_date
	// desc) tt group by
	// conversion_id order by create_date desc
	@Select({ "select", INSERT_FIELDS, ",count(id) as id from ( select * from ", TABLE_NAME,
			"where from_id = #{userId} or to_id = #{userId} order by create_date ) tt group by conversation_id order by create_date desc" })
	List<Message> getConversionList(@Param("userId") int userId);

	@Select({ "select count(id) from", TABLE_NAME,
			"where has_read = 0 and to_id = #{userId} and conversation_id = #{conversationId}" })
	int getConversionCount(@Param("conversationId") String conversationId, @Param("userId") int userId);
}
