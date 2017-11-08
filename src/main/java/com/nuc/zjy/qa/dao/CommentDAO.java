package com.nuc.zjy.qa.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.nuc.zjy.qa.bean.Comment;

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
public interface CommentDAO {
	String TABLE_NAME = "comment";
	String INSERT_FIELDS = "user_id,content,create_date,entity_id,entity_type,status";
	String SELECT_FIELDS = "id," + INSERT_FIELDS;

	@Insert({ "insert into", TABLE_NAME, " (", INSERT_FIELDS,
			") values (#{userId},#{content},#{createDate},#{entityId},#{entityType},#{status})" })
	int addComment(Comment comment);

	@Select({ "select", SELECT_FIELDS, "from", TABLE_NAME, "where id = #{id}" })
	Comment selectByid(int id);

	@Select({ "select", SELECT_FIELDS, "from", TABLE_NAME,
			"where entity_id = #{entityId} and entity_type = #{entityType} order by create_date desc" })
	List<Comment> selectCommentByEntity(@Param("entityId") int entityId, @Param("entityType") int entityType);

	@Select({ "select count(id) from ", TABLE_NAME, " where entity_id = #{entityId} and entity_type = #{entityType} " })
	int getCommentCount(@Param("entityId") int entityId, @Param("entityType") int entityType);

	@Update({ "update ", TABLE_NAME, "set status = #{status}  where id = #{id}" })
	void updateStatus(int status,int id);
}
