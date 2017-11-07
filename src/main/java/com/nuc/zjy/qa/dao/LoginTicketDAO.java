package com.nuc.zjy.qa.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.nuc.zjy.qa.bean.LoginTicket;

/**
 * @项目名称：QA
 * @类名称：LoginTicketDAO @类描述：
 *
 * @author 赵建银
 * @date 2017年11月7日
 * @time 下午2:52:38
 * @version 1.0
 */
public interface LoginTicketDAO {
	String TABLE_NAME = "loginticket";
	String INSERT_FIELDS = "userid,expired,status,ticket";
	String SELECT_FIELDS = "id," + INSERT_FIELDS;

	@Insert({ "insert into ", TABLE_NAME, "(", INSERT_FIELDS, ") values (#{userid},#{expired},#{status},#{ticket})" })
	int addTicket(LoginTicket loginTicket);

	@Select({ "select", INSERT_FIELDS, "from", TABLE_NAME, "where ticket = #{ticket}" })
	LoginTicket selectTicket(String ticket);

	@Update({ "update", TABLE_NAME, "set status = #{status} where ticket = #{ticket}" })
	void updateStatus(@Param("ticket") String ticket, @Param("status") int status);
}
