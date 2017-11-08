package com.nuc.zjy.qa.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.nuc.zjy.qa.bean.Question;

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
public interface QuestionDAO {
	String TABLE_NAME = "question";
	String INSERT_FIELDS = "title,content,create_date,user_id,comment_count";
	String SELECT_FIELDS = "id," + INSERT_FIELDS;

	@Insert({ "insert into", TABLE_NAME, " (", INSERT_FIELDS,
			") values (#{title},#{content},#{createDate},#{userId},#{commentCount})" })
	int addQuestion(Question question);

	@Select({ "select", SELECT_FIELDS, "from", TABLE_NAME, "where id = #{id}" })
	Question selectByid(int id);

	List<Question> selectLastQuestins(@Param("userId") int userId);

	@Update({ "update", TABLE_NAME, "set comment_count = #{commentCount} where id = #{id}" })
	void updateCommentCount(@Param("id") int id, @Param("commentCount") int commentCount);

	@Delete({ "delete from ", TABLE_NAME, " where id = #{id}" })
	void deleteById(int id);
}
